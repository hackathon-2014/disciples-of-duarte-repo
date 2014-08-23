package com.r0adkll.kiosk.session.model;

import com.estimote.sdk.Beacon;

/**
 * Created by r0adkll on 8/23/14.
 */
public class Location {

    public long id;
    public Beacon beacon;
    public String name;
    public long companyId;
    public String companyName;
    public float radius;

    public Location(){}

}
