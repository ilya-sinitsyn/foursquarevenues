package com.example.myfirstandroidapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VenuesModel implements VenuesModelRequests {

    private static final String CLIENT_ID = "xxx";
    private static final String CLIENT_SECRET = "yyy";

    private LocationManager mLocationManager;
    private RequestQueue mRequestQueue;
    private VenuesPresenterOperations mVenuesPresenterOperations;

    public VenuesModel(VenuesPresenterOperations venuesPresenterOperations) {
        mVenuesPresenterOperations = venuesPresenterOperations;
    }

    @Override
    public void fetchVenues(String searchString) {
        if (ContextCompat.checkSelfPermission(
                mVenuesPresenterOperations.getActivityContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            mVenuesPresenterOperations.handleError("You denied application to use location service. " +
                    "You can grant permission to use location services in the device settings.");
            return;
        }

        Location location = getLocation();
        if (location == null) {
            mVenuesPresenterOperations.handleError("Failed to get location. Check you allow applications " +
                    "to access your location in the device settings.");
            return;
        }

        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        String requestUrl = "https://api.foursquare.com/v2/venues/search" +
                "?client_id=" + CLIENT_ID +
                "&client_secret=" + CLIENT_SECRET +
                "&v=20130815" +
                "&ll=" + latitude + "," + longitude +
                "&query=" + searchString;

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mVenuesPresenterOperations.getActivityContext());
        }
        else {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                    @Override
                    public boolean apply(Request<?> request) {
                        return true;
                    }
            });
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl,
                new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            mVenuesPresenterOperations.processData(response);
                        }
                },
                new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mVenuesPresenterOperations.handleError(
                                    "Failed to retrieve data, check network connection.");
                        }
                });

        mRequestQueue.add(stringRequest);
    }

    private Location getLocation() {
        Location location = null;
        try {
            if (mLocationManager == null) {
                mLocationManager = (LocationManager) mVenuesPresenterOperations.
                        getActivityContext().getSystemService(Context.LOCATION_SERVICE);
            }
            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return location;
    }
}
