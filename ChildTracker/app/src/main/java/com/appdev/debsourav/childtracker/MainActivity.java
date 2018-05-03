package com.appdev.debsourav.childtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView txtCallLog;
    static DatabaseReference callRef, msgRef, mRef;
    FirebaseAuth auth;
<<<<<<< HEAD

=======
>>>>>>> 1aa585a95ea75bf761df43da9be5a618a52961cd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth= FirebaseAuth.getInstance();

        mRef= FirebaseDatabase.getInstance().getReference().child("Childs").child(auth.getCurrentUser().getUid()+"");
        callRef= FirebaseDatabase.getInstance().getReference("Calls");
        msgRef= FirebaseDatabase.getInstance().getReference("Messages");






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
