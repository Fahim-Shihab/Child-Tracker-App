package com.appdev.shan.childtracker;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CallLogStats {
    public static void getCallDetails(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return "TODO";
        }

        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
       // Firebase mFire = new Firebase("https://trackphone-a6f55.firebaseio.com/CallLog");
        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(number);
            String callername = cursor.getString(name);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
            Calendar today = Calendar.getInstance();
            today.clear(Calendar.HOUR);
            today.clear(Calendar.MINUTE);
            today.clear(Calendar.SECOND);
            Date todayDate = today.getTime();
            Date dateRange = addDays(todayDate,-3);
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

            if(!(callDayTime.before(dateRange))) {
                if(phNumber.startsWith("+8801")){
                    //Firebase mFireChild = mFire.child(""+callDayTime);
                    Call call = new Call(phNumber, callername, dir, ("" + callDayTime), callDuration);
                    //mFireChild.setValue(call);

                    stringBuffer.append("\nPhone Number:--- " + phNumber + "\nName:---"+callername+" \nCall Type:--- "
                            + dir + " \nCall Date:--- " + callDayTime
                            + " \nCall duration in sec :--- " + callDuration);
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
                }
            }
        }
        cursor.close();
        //return stringBuffer.toString();
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }
}
