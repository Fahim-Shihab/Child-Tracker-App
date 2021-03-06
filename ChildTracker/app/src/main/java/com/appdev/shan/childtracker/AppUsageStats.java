package com.appdev.shan.childtracker;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import com.appdev.debsourav.childtracker.AppStats;
//import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AppUsageStats {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
    public static final String TAG = AppStats.class.getSimpleName();

    @SuppressWarnings("ResourceType")
    public static void getStats(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        int interval = UsageStatsManager.INTERVAL_YEARLY;
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        UsageEvents uEvents = usm.queryEvents(startTime,endTime);
        while (uEvents.hasNextEvent()){
            UsageEvents.Event e = new UsageEvents.Event();
            uEvents.getNextEvent(e);

            if (e != null){
                Log.d(TAG, "Event: " + e.getPackageName() + "\t" +  e.getTimeStamp());
            }
        }
    }

    public static List<UsageStats> getUsageStatsList(Context context){
        UsageStatsManager usm = getUsageStatsManager(context);
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.HOUR, -12);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range Start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range End:" + dateFormat.format(endTime));

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                startTime,endTime);
        return usageStatsList;
    }

    public static void printUsageStats(List<UsageStats> usageStatsList){

        //Firebase mFire = new Firebase("https://trackphone-a6f55.firebaseio.com/AppLog");

        for (UsageStats u : usageStatsList){

            String packname;
            packname = u.getPackageName();
            String appname = packname.replaceAll("\\."," ");

            if(appname.startsWith("com ")) appname = appname.replace("com ","");

            if(appname.startsWith("org ")) appname = appname.replace("org ","");

            System.out.println("Appname: "+appname);

            if((u.getTotalTimeInForeground()/1000)>0) {

                Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: "
                        + u.getTotalTimeInForeground() / 1000);

                /*mFire.child("Package: " + appname).
                        setValue(" Time: " + (u.getTotalTimeInForeground() / (1000 * 60)) +
                                " minutes " + (u.getTotalTimeInForeground() / 1000) % (60) + " seconds");
           */ }
        }
    }

    public static void printCurrentUsageStatus(Context context){
        printUsageStats(getUsageStatsList(context));
    }
    @SuppressWarnings("ResourceType")
    private static UsageStatsManager getUsageStatsManager(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        return usm;
    }
}
