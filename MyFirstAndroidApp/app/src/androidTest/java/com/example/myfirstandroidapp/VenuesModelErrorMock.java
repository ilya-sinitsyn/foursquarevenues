package com.example.myfirstandroidapp;

public class VenuesModelErrorMock implements VenuesModelRequests  {

    private VenuesPresenterOperations mVenuesPresenterOperations;

    public void setPresenterOperations(VenuesPresenterOperations venuesPresenterOperations) {
        mVenuesPresenterOperations = venuesPresenterOperations;
    }

    @Override
    public void fetchVenues(String searchString) {
        mVenuesPresenterOperations.handleError("error");
    }
}
