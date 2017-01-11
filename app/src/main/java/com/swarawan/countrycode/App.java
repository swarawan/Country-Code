package com.swarawan.countrycode;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by rioswarawan on 1/11/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
    }
}
