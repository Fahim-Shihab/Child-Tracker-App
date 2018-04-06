package com.appdev.debsourav.childtracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.appdev.shan.childtracker.AppUsageStats;

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
        AppUsageStats.printCurrentUsageStatus(ChildService.this);
        CallHistory.getCallDetails(ChildService.this);
        MessageHistory.getAllSms(ChildService.this);
        return super.onStartCommand(intent,flag,startId);
    }
}
