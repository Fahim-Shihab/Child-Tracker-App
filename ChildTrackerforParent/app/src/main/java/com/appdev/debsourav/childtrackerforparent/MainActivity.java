package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    ImageButton btnMsgLog, Applog, btnCallLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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



    }
}
