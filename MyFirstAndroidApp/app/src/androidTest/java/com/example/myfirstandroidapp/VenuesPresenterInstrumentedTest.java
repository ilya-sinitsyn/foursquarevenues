package com.example.myfirstandroidapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

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
        MainActivityOperationsMock mainActivityOperationsMock =
                new MainActivityOperationsMock(appContext);
        VenuesModelMock venuesModelMock = new VenuesModelMock();
        VenuesPresenter venuesPresenter = new VenuesPresenter( mainActivityOperationsMock,
                venuesModelMock);
        venuesModelMock.setPresenterOperations(venuesPresenter);

        venuesPresenter.processSearchString("sushi");
        assertTrue(mainActivityOperationsMock.isSuccess());
    }

    @Test
    public void processSearchStringModelError() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MainActivityOperationsMock mainActivityOperationsMock =
                new MainActivityOperationsMock(appContext);
        VenuesModelErrorMock venuesModelErrorMock = new VenuesModelErrorMock();
        VenuesPresenter venuesPresenter = new VenuesPresenter(mainActivityOperationsMock,
                venuesModelErrorMock);
        venuesModelErrorMock.setPresenterOperations(venuesPresenter);

        venuesPresenter.processSearchString("sello");
        assertTrue(mainActivityOperationsMock.isFailed());
    }

    @Test
    public void processSearchStringModelInvalidResponse() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MainActivityOperationsMock mainActivityOperationsMock =
                new MainActivityOperationsMock(appContext);
        VenuesModelInvalidResponseMock venuesModelInvalidResponseMock = new VenuesModelInvalidResponseMock();
        VenuesPresenter venuesPresenter = new VenuesPresenter(mainActivityOperationsMock,
                venuesModelInvalidResponseMock);
        venuesModelInvalidResponseMock.setPresenterOperations(venuesPresenter);

        venuesPresenter.processSearchString("sello");
        assertTrue(mainActivityOperationsMock.isFailed());
    }
}
