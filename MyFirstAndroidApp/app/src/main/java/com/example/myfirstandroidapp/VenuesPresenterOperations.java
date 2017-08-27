package com.example.myfirstandroidapp;

import android.content.Context;

import java.util.List;

public interface VenuesPresenterOperations {
    Context getActivityContext();
    void processData(String data);
    void handleError(String errorMessage);
}
