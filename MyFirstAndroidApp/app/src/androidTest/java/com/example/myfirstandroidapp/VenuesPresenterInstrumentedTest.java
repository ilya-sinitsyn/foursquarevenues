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
public class VenuesPresenterInstrumentedTest {

    @Test
    public void processSearchString() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MainActivityOperationsMock mainActivityOperationsMock = new MainActivityOperationsMock();
        VenuesModelMock venuesModelMock = new VenuesModelMock();
        VenuesPresenter venuesPresenter = new VenuesPresenter(appContext, mainActivityOperationsMock,
                venuesModelMock);
        venuesModelMock.setPresenterOperations(venuesPresenter);

        venuesPresenter.processSearchString("sushi");
        assertTrue(mainActivityOperationsMock.isSuccess());
    }

    @Test
    public void processSearchStringModelError() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MainActivityOperationsMock mainActivityOperationsMock = new MainActivityOperationsMock();
        VenuesModelErrorMock venuesModelErrorMock = new VenuesModelErrorMock();
        VenuesPresenter venuesPresenter = new VenuesPresenter(appContext, mainActivityOperationsMock,
                venuesModelErrorMock);
        venuesModelErrorMock.setPresenterOperations(venuesPresenter);

        venuesPresenter.processSearchString("sello");
        assertTrue(mainActivityOperationsMock.isFailed());
    }

    @Test
    public void processSearchStringModelInvalidResponse() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MainActivityOperationsMock mainActivityOperationsMock = new MainActivityOperationsMock();
        VenuesModelInvalidResponseMock venuesModelInvalidResponseMock = new VenuesModelInvalidResponseMock();
        VenuesPresenter venuesPresenter = new VenuesPresenter(appContext, mainActivityOperationsMock,
                venuesModelInvalidResponseMock);
        venuesModelInvalidResponseMock.setPresenterOperations(venuesPresenter);

        venuesPresenter.processSearchString("sello");
        assertTrue(mainActivityOperationsMock.isFailed());
    }
}
