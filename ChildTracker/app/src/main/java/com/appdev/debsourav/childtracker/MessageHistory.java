package com.appdev.debsourav.childtracker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MessageHistory extends AppCompatActivity {

    TextView txtMsgs;
    static String childID= getUserID(FirebaseAuth.getInstance().getCurrentUser().getEmail());

    static DatabaseReference msgRef;

    static String getUserID(String email){
        String str[]= email.split("@");
        return str[0];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_history);
        txtMsgs= findViewById(R.id.txtMsgs);

        //String messagelog = getAllSms(getApplicationContext());
        //txtMsgs.setText(messagelog);

    }

    public static void getAllSms(Context context) {
        msgRef= FirebaseDatabase.getInstance().getReference().child(childID).child("MessageLog");
        msgRef.setValue(null);

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
                    Date date = new Date(Long.valueOf(smsDate));
                    String dateFormat = new SimpleDateFormat("MM/dd/yyyy").format(date);
                    String type = "";
                    Calendar today = Calendar.getInstance();
                    today.clear(Calendar.HOUR);
                    today.clear(Calendar.MINUTE);
                    today.clear(Calendar.SECOND);
                    Date todayDate = today.getTime();
                    Date dateRange = addDays(todayDate, -3);

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

                    if (!(date.before(dateRange))) {
                        if(number.startsWith("+8801")) {
                            stringBuffer.append("\nNumber:---" + number +
                                    " \nBody:--- "
                                    + body + "\nName:--- " + name + " \nDate:--- " + dateFormat
                                    + " \nMessage Type :--- " + type);
                            stringBuffer.append("\n\n");


                            //System.out.println(stringBuffer);

                            Message msg = new Message(number, body, name, "" + dateFormat, type, date + "");
                            msgRef.child("" + date).setValue(msg);
                        }
                }
                    c.moveToNext();

                }
            } else {
                //Toast.makeText(MessageHistory.this, "No message to show!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }
}
