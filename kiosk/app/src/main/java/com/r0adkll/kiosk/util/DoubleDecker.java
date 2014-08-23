package com.r0adkll.kiosk.util;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by r0adkll on 2/10/14.
 * Project: QuarryProject
 * Package: com.r0adkll.quarry.ui
 *
 * Copyright @2014 Drew Heavner. All rights reserved.
 */
public class DoubleDecker {

    /* Otto Bus Singleton */
    public static Bus _instance;

    /**
     * Get the singleton instance of the Otto event bus
     * @return      the Otto Event Bus singleton
     */
    public static Bus getBus(){
        if(_instance == null) _instance = new Bus(ThreadEnforcer.ANY);
        return _instance;
    }

}
