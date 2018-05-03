package com.appdev.debsourav.childtracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CallHistory {

    static String getUserID(String email){
        String str[]= email.split("@");
        return str[0];
    }


    static DatabaseReference callRef;

    static String getCallDetails(Context context) {
        String childID= getUserID(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        callRef=FirebaseDatabase.getInstance().getReference().child(childID).child("CallLog");

        StringBuffer stringBuffer = new StringBuffer();
        try (@SuppressLint("MissingPermission")
             Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC")) {
            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
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
                    if(phNumber.startsWith("+8801")) {
                        stringBuffer.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                                + dir + " \nCall Date:--- " + callDayTime
                                + " \nCall duration in sec :--- " + callDuration);
                        stringBuffer.append("\n----------------------------------");

                        Call call = new Call(phNumber, callername, dir, ("" + callDayTime), callDuration);
                        callRef.child(("" + callDayTime)).setValue(call);
                    }
                }
            }
            cursor.close();
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
