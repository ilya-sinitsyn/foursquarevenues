package com.example.myfirstandroidapp;

public class VenueInfo {
    private String mName;
    private String mAddress;
    private double mDistance;

    public VenueInfo(String name, String address, double distance)
    {
        mName = name;
        mAddress = address;
        mDistance = distance;
    }

    public String toString() {
        return mName + ", " + mAddress + ", " + mDistance + "m";
    }

    public double getDistance() {
        return mDistance;
    }
}

