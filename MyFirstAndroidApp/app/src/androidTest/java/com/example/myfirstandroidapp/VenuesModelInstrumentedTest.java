package com.example.myfirstandroidapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class VenuesModelInstrumentedTest {

    @Test
    public void fetchVenuesList() throws Exception {
        CountDownLatch lock = new CountDownLatch(1);
        Context appContext = InstrumentationRegistry.getTargetContext();
        VenuesPresenterOperationsMock venuesPresenterOperationsMock =
                new VenuesPresenterOperationsMock(appContext);
        VenuesModel venuesModel = new VenuesModel(venuesPresenterOperationsMock);
        assertNotNull(venuesModel);

        venuesModel.fetchVenues("sello");
        lock.await(5000, TimeUnit.MILLISECONDS);
        assertTrue(venuesPresenterOperationsMock.isSuccess());
    }
}
