package com.example.myfirstandroidapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VenuesPresenter implements VenuesPresenterRequests, VenuesPresenterOperations {

    private VenuesModelRequests mVenuesModel;
    private MainActivityOperations mViewOperations;

    public VenuesPresenter(Context activityContext, MainActivityOperations viewOperation) {
        mVenuesModel = new VenuesModel(activityContext, this);
        mViewOperations = viewOperation;
    }

    public VenuesPresenter(Context activityContext, MainActivityOperations viewOperation,
                           VenuesModelRequests venuesModel) {
        mVenuesModel = venuesModel;
        mViewOperations = viewOperation;
    }

    public void processSearchString(String searchString) {
        if (searchString.length() == 0) {
            mViewOperations.displayListOfVenues(new ArrayList<VenueInfo>());
        }
        else {
            mVenuesModel.fetchVenues(searchString);
        }
    }

    public void processData(String data) {
        List<VenueInfo> venueList = new ArrayList<VenueInfo>();
        try {
            JSONObject reader = new JSONObject(data);
            JSONObject responseObject = reader.getJSONObject("response");
            JSONArray venuesArray = responseObject.getJSONArray("venues");
            for (int i = 0; i < venuesArray.length(); i++) {
                JSONObject venueObject = venuesArray.getJSONObject(i);
                String venueName = venueObject.getString("name");

                String venueAddress = "";
                if (venueObject.getJSONObject("location").has("address")) {
                    venueAddress = venueObject.getJSONObject("location").getString("address");
                }
                if (venueObject.getJSONObject("location").has("city")) {
                    venueAddress += " " + venueObject.getJSONObject("location").getString("city");
                }

                double venueDistance = -1;
                if (venueObject.getJSONObject("location").has("distance")) {
                    venueDistance = venueObject.getJSONObject("location").getDouble("distance");
                }

                VenueInfo venueInfo = new VenueInfo(venueName, venueAddress, venueDistance);
                venueList.add(venueInfo);
            }
            mViewOperations.displayListOfVenues(venueList);
        }
        catch (JSONException e) {
            mViewOperations.displayMessageOnVenuesSearchingFailed("Failed to parse venues data.");
        }
    }

    public void handleError(String errorMessage) {
        mViewOperations.displayMessageOnVenuesSearchingFailed(errorMessage);
    }
}
