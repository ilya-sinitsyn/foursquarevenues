package com.example.myfirstandroidapp;

public class VenuesModelInvalidResponseMock implements VenuesModelRequests {

    private VenuesPresenterOperations mVenuesPresenterOperations;

    public void setPresenterOperations(VenuesPresenterOperations venuesPresenterOperations) {
        mVenuesPresenterOperations = venuesPresenterOperations;
    }

    public void fetchVenues(String searchString) {
        mVenuesPresenterOperations.processData("invalid response");
    }
}
