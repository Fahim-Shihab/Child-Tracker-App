package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

<<<<<<< HEAD
=======
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.ValueDataEntry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import static com.appdev.debsourav.childtrackerforparent.ChildList.childID;

>>>>>>> 537bc5ef6b80d1c1ab03ccb0fc934ac0d0506434

public class MainActivity extends AppCompatActivity {

    ImageButton btnMsgLog, Applog, btnCallLog, btnWeekly, btnGPS;
<<<<<<< HEAD
    public static String childID;
=======

    static List<DataEntry> wdata = new ArrayList<>();
    DatabaseReference WeeklyRef;
    static int count =1;
    Map<String,Integer> map;
>>>>>>> 537bc5ef6b80d1c1ab03ccb0fc934ac0d0506434

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
=======
        if(count>1) wdata.clear();

        setTitle("Menu");

        WeeklyRef = FirebaseDatabase.getInstance().getReference().child(ChildList.childID).child("WeeklyLog");

            WeeklyRef.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    int val = dataSnapshot.getValue(Integer.class);
                    String key = dataSnapshot.getKey();
                    //int val = Integer.parseInt(value);
                    int hours = val / 3600;
                    int minutes = val / 60;
                    int seconds = val % 60;
                    String appname = "App Name: " + key;

                    String duration = "\nRun Time: " + hours + " hours " + minutes + " minutes " + seconds + " seconds";
                    System.out.println(appname + minutes);

                    if (minutes > 15 ) {

                            wdata.add(new ValueDataEntry(key, minutes));

                    }
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





>>>>>>> 537bc5ef6b80d1c1ab03ccb0fc934ac0d0506434
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
<<<<<<< HEAD
                startActivity(new Intent(getApplicationContext(), ChartData.class));
=======
                startActivity(new Intent(getApplicationContext(), Weekly.class));
                count++;

>>>>>>> 537bc5ef6b80d1c1ab03ccb0fc934ac0d0506434
            }
        });

        btnGPS = findViewById(R.id.btnGPS);

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

    }
}
