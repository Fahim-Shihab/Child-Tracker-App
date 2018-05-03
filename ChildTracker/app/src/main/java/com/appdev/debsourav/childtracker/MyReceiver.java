package com.appdev.debsourav.childtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyReceiver extends BroadcastReceiver {

    MessageHistory msghist;
    AppStats appst;
    CallHistory cal;

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
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        //root.setValue(null);
        msghist.getAllSms(context);
        cal.getCallDetails(context);
        appst.printCurrentUsageStatus(context);
        Toast.makeText(context, "Updating to Firebase", Toast.LENGTH_LONG).show();
        Log.d("Alarm set","Alarm just fired");
    }
}
