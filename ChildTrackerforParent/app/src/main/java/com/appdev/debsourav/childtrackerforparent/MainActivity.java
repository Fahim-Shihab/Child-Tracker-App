package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    ImageButton btnMsgLog, Applog, btnCallLog, btnWeekly, btnGPS;
<<<<<<< HEAD
    public static String childID;
=======

>>>>>>> 1aa585a95ea75bf761df43da9be5a618a52961cd

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

        btnWeekly = findViewById(R.id.btnAppWeekly);

        btnWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChartData.class));
            }
        });

        btnGPS = findViewById(R.id.btnGPS);

    }
}
