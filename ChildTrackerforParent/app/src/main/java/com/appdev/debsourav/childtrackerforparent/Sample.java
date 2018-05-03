package com.appdev.debsourav.childtrackerforparent;
public class Sample {

    public Sample() {
    }

    private double Lat;
    private double Long;

    public Sample(double lat, double aLong) {
        Lat = lat;
        Long = aLong;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }
}