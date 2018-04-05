package com.appdev.debsourav.childtrackerforparent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MessageBody extends AppCompatActivity {

    TextView txtNumber, txtBody, txtDate, txtType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_body);


        final Message msg=(Message) getIntent().getParcelableExtra("Message");

        txtNumber= findViewById(R.id.detNumber);
        txtBody= findViewById(R.id.detBody);
        txtDate= findViewById(R.id.detDate);
        txtType= findViewById(R.id.detType);

        txtNumber.setText(msg.getNumber());
        txtType.setText("From: "+msg.getType());
        txtDate.setText(msg.getFullDate());
        txtBody.setText(msg.getBody());

    }
}
