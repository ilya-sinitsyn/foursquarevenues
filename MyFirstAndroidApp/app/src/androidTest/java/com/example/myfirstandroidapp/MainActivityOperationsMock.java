package com.example.myfirstandroidapp;

import android.content.Context;

import java.util.List;

public class MainActivityOperationsMock implements MainActivityOperations {

    private Context mAppContext;
    private boolean mSuccessResultReceived = false;
    private boolean mFailedResultReceived = false;

    public MainActivityOperationsMock(Context appContext) {
        mAppContext = appContext;
    }

    @Override
    public Context getActivityContext() {
        return mAppContext;
    }

    @Override
    public void displayListOfVenues(List<VenueInfo> venueList) {
        mSuccessResultReceived = true;
    }

    @Override
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
