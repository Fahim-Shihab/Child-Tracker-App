package com.appdev.debsourav.childtracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        CallHistory.getCallDetails(ChildService.this);
        MessageHistory.getAllSms(ChildService.this);
        AppStats.printCurrentUsageStatus(ChildService.this);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if(hour==23 && minute==59)
        {
            DatabaseReference root = FirebaseDatabase.getInstance().getReference();
            root.setValue(null);
        }

        return super.onStartCommand(intent,flag,startId);
    }
}
