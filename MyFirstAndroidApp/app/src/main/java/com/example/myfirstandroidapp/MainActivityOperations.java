package com.example.myfirstandroidapp;

import java.util.List;

public interface MainActivityOperations {
    void displayListOfVenues(List<VenueInfo> venueList);
    void displayMessageOnVenuesSearchingFailed(String errorMessage);
}
