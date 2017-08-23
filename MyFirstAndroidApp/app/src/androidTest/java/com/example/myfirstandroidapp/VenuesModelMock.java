package com.example.myfirstandroidapp;

public class VenuesModelMock implements VenuesModelRequests {
    private VenuesPresenterOperations mVenuesPresenterOperations;

    public void setPresenterOperations(VenuesPresenterOperations venuesPresenterOperations) {
        mVenuesPresenterOperations = venuesPresenterOperations;
    }

    public void fetchVenues(String searchString) {
        //String response = "{\"meta\":{\"code\":200,\"requestId\":\"5995c66b351e3d5f7d961438\"},\"response\":{\"venues\":[{\"id\":\"5970a791efa82a5693013e8f\",\"name\":\"KOKORO Sushi\",\"contact\":{\"phone\":\"+358407088722\",\"formattedPhone\":\"+358 40 7088722\",\"instagram\":\"kokorohelsinki\",\"facebook\":\"1688877104765906\",\"facebookUsername\":\"kokoro.suomi\",\"facebookName\":\"KOKORO Sushi\"},\"location\":{\"address\":\"Elimäenkatu 9\",\"lat\":60.193974,\"lng\":24.949963,\"labeledLatLngs\":[{\"label\":\"display\",\"lat\":60.193974,\"lng\":24.949963}],\"distance\":648,\"postalCode\":\"00510\",\"cc\":\"FI\",\"neighborhood\":\"Vallila\",\"city\":\"Helsinki\",\"state\":\"Uusimaa\",\"country\":\"Suomi\",\"formattedAddress\":[\"Elimäenkatu 9\",\"00510 Helsinki\",\"Suomi\"]}}]}}";
        String response = "{\"meta\":{\"code\":200,\"requestId\":\"5995c66b351e3d5f7d961438\"}," +
                "\"response\":{\"venues\":[{\"id\":\"5970a791efa82a5693013e8f\",\"name\":" +
                "\"KOKORO Sushi\",\"contact\":{\"phone\":\"+358407088722\",\"formattedPhone\":" +
                "\"+358 40 7088722\",\"instagram\":\"kokorohelsinki\",\"facebook\":\"1688877104765906\"," +
                "\"facebookUsername\":\"kokoro.suomi\",\"facebookName\":\"KOKORO Sushi\"},\"location\":" +
                "{\"address\":\"Elimäenkatu 9\",\"lat\":60.193974,\"lng\":24.949963,\"labeledLatLngs\":" +
                "[{\"label\":\"display\",\"lat\":60.193974,\"lng\":24.949963}],\"distance\":648,\"" +
                "postalCode\":\"00510\",\"cc\":\"FI\",\"neighborhood\":\"Vallila\",\"city\":\"Helsinki\"," +
                "\"state\":\"Uusimaa\",\"country\":\"Suomi\",\"formattedAddress\":[\"Elimäenkatu 9\"," +
                "\"00510 Helsinki\",\"Suomi\"]}}]}}";
        mVenuesPresenterOperations.processData(response);
    }
}
