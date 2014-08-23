package com.r0adkll.kiosk.session.model;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by r0adkll on 8/23/14.
 *
 */
public class Content {

    public long id;
    public Set<Long> locationIds;
    public String url;
    public String name;
    public JSONObject metadata;

    public Content(){}

    /**
     * JSON constructor
     * @param json
     */
    public Content(JSONObject json){
        id = json.optLong("id");
        url = json.optString("url");
        name = json.optString("name");
        locationIds = new HashSet<>();

        JSONArray locIds = json.optJSONArray("location_ids");
        int N = locIds.length();
        for(int i=0; i<N; i++){
            long _id = locIds.optLong(i, -1);
            if(_id != -1)
                locationIds.add(_id);
        }

        metadata = json.optJSONObject("metadata");
    }

}
