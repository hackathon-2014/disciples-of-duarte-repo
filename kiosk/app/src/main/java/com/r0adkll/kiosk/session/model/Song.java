package com.r0adkll.kiosk.session.model;

import org.json.JSONObject;

public class Song{

    long id;
    String name, artist;
    String url;
    int length;

    public Song(JSONObject json){
        name = json.optString("name");
        artist = json.optString("artist");
        id = json.optLong("id");
        url = json.optString("url");
        length = json.optInt("length");
    }

}