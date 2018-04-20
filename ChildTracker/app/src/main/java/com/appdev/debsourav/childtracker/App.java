package com.appdev.debsourav.childtracker;

import java.text.SimpleDateFormat;

public class App {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");

    private String AppName;
    private String Duration;
    private String LastAccessed;

    public App() {
    }

    public App(String AppName, String Duration, String LastAccessed) {
        this.AppName = AppName;
        this.Duration = Duration;
        this.LastAccessed = LastAccessed;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getLastAccessed() {
        return LastAccessed;
    }

    public void setLastAccessed(String LastAccessed) {
        this.LastAccessed = LastAccessed;
    }
}
