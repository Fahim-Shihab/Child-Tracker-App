package com.appdev.debsourav.childtracker;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AppStats {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
    public static final String TAG = AppStats.class.getSimpleName();

    static DatabaseReference appRef = FirebaseDatabase.getInstance()
            .getReference("Shan/AppLog");

    @SuppressWarnings("ResourceType")
    public static void getStats(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        int interval = UsageStatsManager.INTERVAL_WEEKLY;
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        long startTime = calendar.getTimeInMillis();

/*        Log.d(TAG, "Range start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));*/

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
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int back = (-1)*hour*60-minute;
        calendar.add(Calendar.MINUTE, back);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range Start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range End:" + dateFormat.format(endTime));

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                startTime,endTime);
        return usageStatsList;
    }

    public static void printUsageStats(List<UsageStats> usageStatsList){

        for (UsageStats u : usageStatsList){

            String packname;
            packname = u.getPackageName();
            String appname = packname.replaceAll("\\."," ");

            if(appname.startsWith("com ")) appname = appname.replace("com ","");

            if(appname.startsWith("org ")) appname = appname.replace("org ","");

            System.out.println("Appname: "+appname);

            if((u.getTotalTimeInForeground()/1000)>0) {

                /*Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: "
                        + u.getTotalTimeInForeground() / 1000);*/

                appRef.child("Package: " + appname).
                        setValue(" Time: " + (u.getTotalTimeInForeground() / (1000 * 60)) +
                        " minutes " + (u.getTotalTimeInForeground() / 1000) % (60) + " seconds");
            }
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
