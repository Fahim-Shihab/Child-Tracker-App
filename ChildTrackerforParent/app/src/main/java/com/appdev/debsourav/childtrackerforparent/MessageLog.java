package com.appdev.debsourav.childtrackerforparent;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

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
            protected void populateViewHolder(final MsgViewHolder viewHolder, final Message model, int position) {
                viewHolder.setNumber(model.getNumber());
                viewHolder.setDate(model.getDate());
                viewHolder.setBody(model.getBody());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(getApplicationContext(),MessageBody.class);

                        final Pair[] pairs= new Pair[4];

                        pairs[0]= new Pair(viewHolder.msgIcon,"msgLogo");
                        pairs[1]= new Pair(viewHolder.txtNumber,"no");
                        pairs[2]= new Pair(viewHolder.txtBody,"body");
                        pairs[3]= new Pair(viewHolder.txtDate,"date");

                        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MessageLog.this, pairs);

                        intent.putExtra("Message",model);

                        startActivity(intent, options.toBundle());

                    }
                });
            }
        };

        msgRView.setAdapter(firebaseRecyclerAdapter);

    }





}