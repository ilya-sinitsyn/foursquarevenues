package com.example.myfirstandroidapp;

import java.util.List;

public interface VenuesPresenterListener {
    public void onVenuesReady(List<VenueInfo> venuesList);
    public void onError();
}

