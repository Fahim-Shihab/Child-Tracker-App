package com.appdev.mohin.childtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appdev.debsourav.childtracker.R;


public class MapMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_main);

        startService(new Intent(getBaseContext(),MySerVice.class));
    }
}
