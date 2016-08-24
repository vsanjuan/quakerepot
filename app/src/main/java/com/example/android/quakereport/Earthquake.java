package com.example.android.quakereport;

/**
 * Created by Salvador on 24/08/2016.
 */
public class Earthquake {

    double mMagnitude;
    String mLocation;
    String mDate;

    public Earthquake(double magnitude, String location, String date) {

        mMagnitude = magnitude;
        mLocation = location;
        setmDate(date);

    }

    public void setmDate(String date) {
        this.mDate = date;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }
}
