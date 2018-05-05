package com.appdev.debsourav.childtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    TextView txtCallLog;
    static DatabaseReference callRef, msgRef, mRef;
    FirebaseAuth auth;

    MessageHistory msghist;
    AppStats appst;
    CallHistory cal;

    public MainActivity()
    {}

    public MainActivity(MessageHistory msghist, AppStats appst, CallHistory cal)
    {
        this.msghist = msghist;
        this.appst = appst;
        this.cal = cal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth= FirebaseAuth.getInstance();

        /*mRef= FirebaseDatabase.getInstance().getReference().child("Childs").child(auth.getCurrentUser().getUid()+"");
        /*callRef= FirebaseDatabase.getInstance().getReference("Calls");
        msgRef= FirebaseDatabase.getInstance().getReference("Messages");*/






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
<<<<<<< HEAD

        /*Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);*/
=======
        if (AppStats.getUsageStatsList(this).isEmpty()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }
        startService(new Intent(getBaseContext(),MySerVice.class));
        msghist.getAllSms(this);
        cal.getCallDetails(this);
        appst.printCurrentUsageStatus(this);


>>>>>>> 537bc5ef6b80d1c1ab03ccb0fc934ac0d0506434
    }
}
