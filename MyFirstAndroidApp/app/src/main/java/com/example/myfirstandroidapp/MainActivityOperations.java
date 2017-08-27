package com.example.myfirstandroidapp;

import android.content.Context;

import java.util.List;

public interface MainActivityOperations {
    Context getActivityContext();
    void displayListOfVenues(List<VenueInfo> venueList);
    void displayMessageOnVenuesSearchingFailed(String errorMessage);
}
