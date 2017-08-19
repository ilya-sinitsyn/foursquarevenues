package com.example.myfirstandroidapp;

import java.util.Comparator;

public class VenueInfoComparator implements Comparator<VenueInfo> {
    public int compare(VenueInfo left, VenueInfo right) {
        if (left.getDistance() < right.getDistance()) {
            return -1;
        }
        else if (left.getDistance() > right.getDistance()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
