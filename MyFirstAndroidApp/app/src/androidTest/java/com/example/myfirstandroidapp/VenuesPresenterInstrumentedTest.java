package com.example.myfirstandroidapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class VenuesPresenterInstrumentedTest {

    @Test
    public void fetchVenuesList() throws Exception {
        CountDownLatch lock = new CountDownLatch(1);
        final List<VenueInfo> results = new ArrayList<VenueInfo>();;

        Context appContext = InstrumentationRegistry.getTargetContext();
        VenuesPresenter venuesPresenter = new VenuesPresenter(appContext);
        assertNotNull(venuesPresenter);
        venuesPresenter.fetchVenues("sello", new VenuesPresenterListener() {
            @Override
            public void onVenuesReady(List<VenueInfo> venuesList) {
                results.addAll(venuesList);
            }

            @Override
            public void onError() {
            }
        });

        lock.await(5000, TimeUnit.MILLISECONDS);
        assertTrue(results.size() > 0);
    }
}
