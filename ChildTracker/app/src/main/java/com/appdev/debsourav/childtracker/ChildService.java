package com.appdev.debsourav.childtracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;

public class ChildService extends Service {
    public ChildService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flag, int startId)
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        //if(hour==23 && minute==59)


        /*    MessageHistory.getAllSms(ChildService.this);
            CallHistory.getCallDetails(ChildService.this);
            AppStats.printCurrentUsageStatus(ChildService.this);
            Toast.makeText(this, "Updating to Firebase", Toast.LENGTH_LONG).show();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.amico);
        mp.start();
        mp.setLooping(true);*/

        return Service.START_REDELIVER_INTENT;

    }
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
    }
}
