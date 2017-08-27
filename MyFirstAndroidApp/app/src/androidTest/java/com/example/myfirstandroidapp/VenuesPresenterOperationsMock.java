package com.example.myfirstandroidapp;

import android.content.Context;

public class VenuesPresenterOperationsMock implements VenuesPresenterOperations {

    private Context mAppContext;
    private boolean mSuccessResultReceived = false;
    private boolean mFailedResultReceived = false;
    private String mData;

    public VenuesPresenterOperationsMock(Context appContext) {
        mAppContext = appContext;
    }

    @Override
    public Context getActivityContext() {
        return mAppContext;
    }

    @Override
    public void processData(String data) {
        mSuccessResultReceived = true;
        mData = data;
    }

    @Override
    public void handleError(String errorMessage) {
        mFailedResultReceived = true;
    }

    public boolean isSuccess() {
        return mSuccessResultReceived && mData.length() > 0;
    }

    public boolean isFailed() {
        return mFailedResultReceived;
    }
}
