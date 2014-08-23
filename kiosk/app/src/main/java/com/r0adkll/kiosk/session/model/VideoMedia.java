package com.r0adkll.kiosk.session.model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by r0adkll on 8/23/14.
 */
public class VideoMedia {

    public long id;
    public String url;
    public String name;
    public long length; // Length in seconds
    public Map<String, Object> metadata;

    public VideoMedia(){}

    public VideoMedia(JSONObject json){
        id = json.optLong("id");
        url = json.optString("url");
        name = json.optString("name");
        length = json.optLong("length");

        metadata = new HashMap<>();
        JSONObject meta = json.optJSONObject("metadata");
        Iterator<String> keys = meta.keys();
        while(keys.hasNext()){
            String key = keys.next();
            Object data = meta.opt(key);
            metadata.put(key, data);
        }
    }

}
