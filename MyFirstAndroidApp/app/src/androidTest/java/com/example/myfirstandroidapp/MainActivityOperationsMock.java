package com.example.myfirstandroidapp;

import java.util.List;

public class MainActivityOperationsMock implements MainActivityOperations {

    private boolean mSuccessResultReceived = false;
    private boolean mFailedResultReceived = false;

    public void displayListOfVenues(List<VenueInfo> venueList) {
        mSuccessResultReceived = true;
    }

    public void displayMessageOnVenuesSearchingFailed(String errorMessage) {
        mFailedResultReceived = true;
    }

    public boolean isSuccess() {
        return mSuccessResultReceived;
    }

    public boolean isFailed() {
        return mFailedResultReceived;
    }
}
