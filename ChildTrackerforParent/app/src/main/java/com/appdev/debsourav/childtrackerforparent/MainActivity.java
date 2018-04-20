package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.anychart.anychart.DataEntry;
import com.anychart.anychart.ValueDataEntry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton btnMsgLog, Applog, btnCallLog, btnWeekly, btnGPS;

    DatabaseReference WeeklyRef;
    static List<DataEntry> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeeklyRef= FirebaseDatabase.getInstance().getReference("Shan/WeeklyLog");

        WeeklyRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int val = dataSnapshot.getValue(Integer.class);
                String key = dataSnapshot.getKey();

                //int val = Integer.parseInt(value);
                int hours = val/3600;
                int minutes = val/60;
                int seconds = val%60;
                String appname = "App Name: "+key;

                String duration = "\nRun Time: "+hours+" hours "+minutes+" minutes "+seconds+" seconds";
                System.out.println(appname+minutes);
                if(minutes>2) data.add(new ValueDataEntry(key,minutes));
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
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnMsgLog= findViewById(R.id.btnMsg);

        btnMsgLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MessageLog.class));
            }
        });

        btnCallLog= findViewById(R.id.btnCall);

        btnCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CallLog.class));
            }
        });

        Applog= findViewById(R.id.btnApp);

        Applog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AppLog.class));
            }
        });

        btnWeekly = findViewById(R.id.btnAppWeekly);

        btnWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Weekly.class));
            }
        });

        btnGPS = findViewById(R.id.btnGPS);

    }
}
