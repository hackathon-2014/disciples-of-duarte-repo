package com.r0adkll.kiosk.session.model;

import com.estimote.sdk.Beacon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r0adkll on 8/23/14.
 */
public class Location {

    public long id;
    public Beacon beacon;
    public String name;
    public long companyId;
    public String companyName;
    public String companyIcon;
    public float range;
    public List<String> categories = new ArrayList<>();

    public Location(){}

    public Location(JSONObject json){
        id = json.optLong("id");
        name = json.optString("name");
        companyId = json.optLong("company_id");
        companyName = json.optString("company_name");
        companyIcon = json.optString("company_icon");
        range = (float) json.optDouble("range");

//        JSONObject beaconJson = json.optJSONObject("beacon");
//        String proxUUID = beaconJson.optString("uuid");
//        String mac_addr = beaconJson.optString("mac");
//        int major = beaconJson.optInt("major");
//        int minor = beaconJson.optInt("minor");
//        beacon = new Beacon(proxUUID, "", mac_addr, major, minor, 0, 0);

        JSONArray cats = json.optJSONArray("categories");
        int N = cats.length();
        for(int i=0; i<N; i++){
            String category = cats.optString(i);
            categories.add(category);
        }

    }

}
