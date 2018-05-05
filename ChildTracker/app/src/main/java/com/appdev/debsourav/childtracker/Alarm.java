package com.appdev.debsourav.childtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {

    Button buttonClock;
    TimePicker TimePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        setTitle("Alarm");

        buttonClock = findViewById(R.id.alarmClockButton);
        TimePick = findViewById(R.id.TimePicker);

        buttonClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                if (Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            TimePick.getHour(), TimePick.getMinute(), 0);
                }
                else{
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            TimePick.getCurrentHour(), TimePick.getCurrentMinute(), 0);
                }

                setAlarm(calendar.getTimeInMillis());
                Toast.makeText(Alarm.this,"Alarm set",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setAlarm(long time) {

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this, MyReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //setting the repeating alarm that will be fired every day

        am.setRepeating(AlarmManager.RTC, time,AlarmManager.INTERVAL_HOUR, pi);

        am.setRepeating(AlarmManager.RTC, time,180000, pi);
        startService(new Intent(this,ChildService.class));
        //am.setRepeating(AlarmManager.RTC, time,180000, pir);

    }
}
