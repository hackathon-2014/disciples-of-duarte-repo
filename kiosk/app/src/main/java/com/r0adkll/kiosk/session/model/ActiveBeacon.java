package com.r0adkll.kiosk.session.model;

import com.estimote.sdk.Beacon;

/**
 * Created by r0adkll on 8/23/14.
 */
public class ActiveBeacon {

    public Beacon beacon;

    public ActiveBeacon(Beacon beacon){
        this.beacon = beacon;
    }

}
