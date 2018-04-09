package com.appdev.shan.childtracker;

public class Call {
    private String phNumber, caller, callType, callDate, callDuration;

    public Call() {
    }

    public Call(String phNumber,String caller, String callType, String callDate, String callDuration) {
        this.phNumber = phNumber;
        this.caller = caller;
        this.callType = callType;
        this.callDate = callDate;
        this.callDuration = callDuration;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getCallerName() {
        return caller;
    }

    public void setCallerName(String caller) {
        this.caller = caller;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
}
