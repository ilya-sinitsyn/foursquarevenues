package com.example.myfirstandroidapp;

public class VenuesPresenterOperationsMock implements VenuesPresenterOperations {

    private boolean mSuccessResultReceived = false;
    private boolean mFailedResultReceived = false;
    private String mData;

    public void processData(String data) {
        mSuccessResultReceived = true;
        mData = data;
    }

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
