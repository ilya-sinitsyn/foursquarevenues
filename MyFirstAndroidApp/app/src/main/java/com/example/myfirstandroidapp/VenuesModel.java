package com.example.myfirstandroidapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VenuesModel {

    private LocationManager mLocationManager;
    private RequestQueue mRequestQueue;
    private Context mActivityContext;

    public VenuesModel(Context activityContext) {
        this.mActivityContext = activityContext;
    }

    public void fetchVenues(String searchString, final VenuesModelListener venuesModelListener) {
        Location location = getLocation();
        if (location == null) {
            venuesModelListener.onError();
        }

        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        String requestUrl = "https://api.foursquare.com/v2/venues/search" +
            "?client_id=xxx" +
            "&client_secret=yyy" +
            "&v=20130815" +
            "&ll=" + latitude + "," + longitude +
            "&query=" + searchString;

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mActivityContext);
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
                    venuesModelListener.onDataReady(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    venuesModelListener.onError();
                }
            });

        mRequestQueue.add(stringRequest);
    }


    private Location getLocation() {
        Location locationGPS = null;
        try {
            if (mLocationManager == null) {
                mLocationManager = (LocationManager) mActivityContext.getSystemService(Context.LOCATION_SERVICE);
            }
            locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        return locationGPS;
    }
}
