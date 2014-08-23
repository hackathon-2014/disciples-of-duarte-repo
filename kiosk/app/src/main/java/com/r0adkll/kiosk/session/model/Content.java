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

    public static final String MOVIE = "movie";
    public static final String TVSHOW = "tvshow";
    public static final String MAGAZINE = "magazine";
    public static final String MUSIC = "music";

    public long id;
    public String type;
    public Set<Long> locationIds;
    public String url;
    public String name;
    public String description;
    public JSONObject metadata;

    public Content(){}

    /**
     * JSON constructor
     * @param json
     */
    public Content(JSONObject json){
        id = json.optLong("id");
        type = json.optString("type");
        url = json.optString("url");
        name = json.optString("name");
        description = json.optString("description");
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

    public String getInfoFromMetaData(){
        switch (type){
            case MOVIE:
                int length = metadata.optInt("length", 0);
                int minutes = length / 60;

                String mpaa = metadata.optString("mpaa", "N/A");
                String date = metadata.optString("date", "N/A");

                return String.format("%s minutes | %s | %s", String.valueOf(minutes), mpaa, date);
            case TVSHOW:
                int length2 = metadata.optInt("length", 0);
                int minutes2 = length2 / 60;

                String vchip = metadata.optString("v_chip", "N/A");
                String date2 = metadata.optString("date", "N/A");

                return String.format("%s minutes | %s | %s", String.valueOf(minutes2), vchip, date2);
            case MAGAZINE:


                break;
            case MUSIC:

                break;
        }
        return "";
    }

}
