package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    DatabaseReference mRef;
    RecyclerView recyclerView;
    Button btnMsgLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRef= FirebaseDatabase.getInstance().getReference("Calls");
        recyclerView= findViewById(R.id.rView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnMsgLog= findViewById(R.id.btnMsg);

        btnMsgLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MessageLog.class));
            }
        });

        FirebaseRecyclerAdapter <Call, CallViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter <Call, CallViewHolder>
                (Call.class, R.layout.call_card, CallViewHolder.class, mRef) {
            @Override
            protected void populateViewHolder(CallViewHolder viewHolder, Call model, int position) {
                viewHolder.setCaller(model.getPhNumber());
                viewHolder.setCallType(model.getCallType());
                viewHolder.setCallDate(model.getCallDate());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
