package com.appdev.debsourav.childtrackerforparent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.appdev.debsourav.childtrackerforparent.ChildList.childID;

public class AppLog extends AppCompatActivity {
    
    DatabaseReference appRef;
    private ArrayList<String> Applog = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_log);

        setTitle("App Log");

        appRef = FirebaseDatabase.getInstance().getReference().child(childID).child("AppLog");

        recyclerView= findViewById(R.id.AppView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerAdapter<App, AppViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter <App, AppViewHolder>
                (App.class, R.layout.app_card, AppViewHolder.class, appRef) {
            @Override
            protected void populateViewHolder(AppViewHolder viewHolder, App model, int position) {
                viewHolder.setAppName(model.getAppName());
                viewHolder.setDuration(model.getDuration());
                viewHolder.setLastAccessed(model.getLastAccessed());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
