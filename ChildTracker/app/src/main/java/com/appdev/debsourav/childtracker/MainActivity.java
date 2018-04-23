package com.appdev.debsourav.childtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
=======
public class MainActivity extends AppCompatActivity {
>>>>>>> 7398de2f23a4cab55d749ace029e221c6374944c


<<<<<<< HEAD
public class MainActivity extends AppCompatActivity {
    TextView txtCallLog;
    static DatabaseReference callRef, msgRef, mRef;
    FirebaseAuth auth;
=======
>>>>>>> 7398de2f23a4cab55d749ace029e221c6374944c

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth= FirebaseAuth.getInstance();

<<<<<<< HEAD
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
=======
//        startService(new Intent(this, ChildService.class));
>>>>>>> 7398de2f23a4cab55d749ace029e221c6374944c


        ImageButton buttonAlarm = findViewById(R.id.alarmSet);

        buttonAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Alarm.class);
                startActivity(intent);
            }});

        ImageButton buttonCalculator = findViewById(R.id.CalculatorSet);

        buttonCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Calculator.class);
                startActivity(intent);
            }});

        /*Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);*/
    }
}
