package com.example.myfirstandroidapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VenuesPresenter {

    private VenuesModel mVenuesModel;

    public VenuesPresenter(Context activityContext) {
        mVenuesModel = new VenuesModel(activityContext);
    }

    public void fetchVenues(String searchString, final VenuesPresenterListener venuesPresenterListener) {
        mVenuesModel.fetchVenues(searchString, new VenuesModelListener() {
            @Override
            public void onDataReady(String data) {
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
                    venuesPresenterListener.onVenuesReady(venueList);
                }
                catch (JSONException e) {
                    venuesPresenterListener.onError();
                }
            }

            @Override
            public void onError() {
                venuesPresenterListener.onError();
            }
        });
    }
}
