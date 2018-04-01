package com.appdev.debsourav.childtrackerforparent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageLog extends AppCompatActivity {

    DatabaseReference msgRef;
    RecyclerView msgRView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_log);

        msgRef= FirebaseDatabase.getInstance().getReference("Messages");
        msgRView= findViewById(R.id.msgRView);
        msgRView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerAdapter<Message, MsgViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter <Message, MsgViewHolder>
                (Message.class, R.layout.msg_card, MsgViewHolder.class, msgRef) {
            @Override
            protected void populateViewHolder(MsgViewHolder viewHolder, Message model, int position) {
                viewHolder.setNumber(model.getNumber());
                viewHolder.setType(model.getType());
                viewHolder.setBody(model.getBody());
            }
        };

        msgRView.setAdapter(firebaseRecyclerAdapter);

    }





}