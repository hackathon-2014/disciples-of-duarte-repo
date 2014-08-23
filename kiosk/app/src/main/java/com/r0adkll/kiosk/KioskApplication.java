package com.r0adkll.kiosk;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                .diskCache(new UnlimitedDiscCache(getCacheDir()))
                .build();

        ImageLoader.getInstance().init(config);

    }
}
