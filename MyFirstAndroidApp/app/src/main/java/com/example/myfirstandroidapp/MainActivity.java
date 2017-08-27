package com.example.myfirstandroidapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityOperations {

    private static final int PERMISSION_REQUEST_LOCATION = 0;
    private VenuesPresenterRequests mVenuesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVenuesPresenter = new VenuesPresenter(this);

        requestPermissions();
        startSearchTextChangesListener();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_LOCATION: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showErrorMessage("Application cannot search venues without access to the " +
                            "location services. You can grant permission to use location services" +
                            "in the device settings.");
                }
            }
        }
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void displayListOfVenues(List<VenueInfo> venueList) {
        Collections.sort(venueList, new VenueInfoComparator());
        ArrayAdapter adapter = new ArrayAdapter<VenueInfo>(this, R.layout.activity_listview, venueList);
        ListView listView = (ListView) findViewById(R.id.venue_list);
        listView.setAdapter(adapter);
    }

    @Override
    public void displayMessageOnVenuesSearchingFailed(String errorMessage) {
        showErrorMessage(errorMessage);
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_REQUEST_LOCATION );
        }
    }

    private void startSearchTextChangesListener() {
        EditText searchTextView = (EditText) findViewById(R.id.search_text_input);
        searchTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mVenuesPresenter.processSearchString(s.toString());
                }
        });
    }

    private void showErrorMessage(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errorMessage);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Error!");
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.show();
    }
}
