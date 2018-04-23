package com.appdev.shan.childtracker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MessageLogStats {
    public static String getAllSms(Context context) {

        StringBuffer stringBuffer = new StringBuffer();
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;
        //Firebase mFire = new Firebase("https://trackphone-a6f55.firebaseio.com/MessageLog");
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

                    if (!(dateFormat.before(dateRange))) {

                        Message msg = new Message(number, body, name, "" + dateFormat, type, dateFormat + "");

                       /* Firebase mFireChild = mFire.child(dateFormat.toString());
                        mFireChild.setValue(msg);*/

                        stringBuffer.append("\nNumber:---" + number +
                                " \nBody:--- "
                                + body + "\nName:--- " + name + " \nDate:--- " + dateFormat
                                + " \nMessage Type :--- " + type);
                        stringBuffer.append("\n\n");

                       /* mFire.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                String value = dataSnapshot.getValue(String.class);
                                String key = dataSnapshot.getKey();
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });*/
                        c.moveToNext();
                    }

                }
            } else {
                //Toast.makeText(this, "No message to show!", Toast.LENGTH_SHORT).show();
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
