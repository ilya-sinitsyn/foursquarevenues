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
        String result = mName;
        if (mAddress != null && mAddress.length() > 0) {
            result += ", " + mAddress;
        }
        if (mDistance >= 0) {
            result += ", " + mDistance + "m";
        }
        return result;
    }

    public double getDistance() {
        return mDistance;
    }
}

