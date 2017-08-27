package com.example.myfirstandroidapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class VenuesPresenter implements VenuesPresenterRequests, VenuesPresenterOperations {

    private VenuesModelRequests mVenuesModel;
    private WeakReference<MainActivityOperations> mViewOperations;

    public VenuesPresenter(MainActivityOperations viewOperation) {
        mVenuesModel = new VenuesModel(this);
        mViewOperations = new WeakReference<MainActivityOperations>(viewOperation);
    }

    public VenuesPresenter(MainActivityOperations viewOperation,
                           VenuesModelRequests venuesModel) {
        mVenuesModel = venuesModel;
        mViewOperations = new WeakReference<MainActivityOperations>(viewOperation);
    }

    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void processSearchString(String searchString) {
        if (searchString.length() == 0) {
            try {
                getView().displayListOfVenues(new ArrayList<VenueInfo>());
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        else {
            mVenuesModel.fetchVenues(searchString);
        }
    }

    @Override
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
            getView().displayListOfVenues(venueList);
        }
        catch (JSONException jsonException) {
            try {
                getView().displayMessageOnVenuesSearchingFailed("Failed to parse venues data.");
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleError(String errorMessage) {
        try {
            getView().displayMessageOnVenuesSearchingFailed(errorMessage);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private MainActivityOperations getView() throws NullPointerException{
        if ( mViewOperations != null )
            return mViewOperations.get();
        else
            throw new NullPointerException("View is unavailable");
    }
}
