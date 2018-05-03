package com.appdev.debsourav.childtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    MessageHistory msghist;
    AppStats appst;
    CallHistory cal;
    static MediaPlayer mp;

    public MyReceiver()
    {}

    public MyReceiver(MessageHistory msghist, AppStats appst, CallHistory cal)
    {
        this.msghist = msghist;
        this.appst = appst;
        this.cal = cal;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        //root.setValue(null);
        msghist.getAllSms(context);
        cal.getCallDetails(context);
        appst.printCurrentUsageStatus(context);
        mp = MediaPlayer.create(context, R.raw.amico);
        mp.start();
        mp.setLooping(true);

        Toast.makeText(context, "Updating to Firebase", Toast.LENGTH_LONG).show();
        Log.d("Alarm set","Alarm just fired");
    }
}
