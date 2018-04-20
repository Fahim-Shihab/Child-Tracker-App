package com.appdev.debsourav.childtrackerforparent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AppLog extends AppCompatActivity {

    ListView applist;
    DatabaseReference appRef;
    private ArrayList<String> Applog = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_log);

        applist = findViewById(R.id.AppLogList);

        appRef= FirebaseDatabase.getInstance().getReference("Shan/AppLog");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Applog);

        applist.setAdapter(arrayAdapter);

        appRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int val = dataSnapshot.getValue(Integer.class);
                String key = dataSnapshot.getKey();

                int hours = val/3600;
                int minutes = val/60;
                int seconds = val%60;

                String appname = "App Name: "+key;
                String duration = "\nRun Time: "+hours+" hours "+minutes+" minutes "+seconds+" seconds";

                //System.out.println(appname+duration);
                Applog.add(appname+duration);
                arrayAdapter.notifyDataSetChanged();
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

    }
}
