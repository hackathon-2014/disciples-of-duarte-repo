package com.r0adkll.kiosk;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by r0adkll on 8/23/14.
 */
public class KioskApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        // Setup Timber
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

    }
}
