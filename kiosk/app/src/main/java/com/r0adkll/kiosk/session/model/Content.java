package com.r0adkll.kiosk.session.model;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by r0adkll on 8/23/14.
 *
 */
public class Content implements Parcelable{

    public static final String MOVIE = "movies";
    public static final String TVSHOW = "tvshows";
    public static final String MAGAZINE = "magazines";
    public static final String MUSIC = "music";

    public long id;
    public String type;
    public List<Long> locationIds;
    public String url;
    public String name;
    public String description;
    public String summary;
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
        summary = json.optString("summary");
        locationIds = new ArrayList<>();

        JSONArray locIds = json.optJSONArray("location_ids");
        int N = locIds.length();
        for(int i=0; i<N; i++){
            long _id = locIds.optLong(i, -1);
            if(_id != -1)
                locationIds.add(_id);
        }

        metadata = json.optJSONObject("metadata");
    }

    public Content(Parcel in){
        id = in.readLong();
        type = in.readString();

        int N = in.readInt();
        long[] ids = new long[N];
        in.readLongArray(ids);
        locationIds = new ArrayList<>();
        for(long lid: ids){
            locationIds.add(lid);
        }

        url = in.readString();
        name = in.readString();
        description = in.readString();
        summary = in.readString();
        try {
            metadata = new JSONObject(in.readString());
        } catch (JSONException e) {
            metadata = new JSONObject();
        }
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

    public String getBannerUri(){
        switch (type){
            case MOVIE:
                return "assets://movies/" + metadata.optString("banner");
            case MAGAZINE:
                return "assets://books/" + metadata.optString("banner");
            case MUSIC:
                return "assets://music/" + metadata.optString("banner");
        }
        return "";
    }

    public String getPosterUri(){
        switch (type){
            case MOVIE:
                return "assets://movies/" + metadata.optString("poster");
            case MAGAZINE:
                return "assets://books/" + metadata.optString("poster");
            case MUSIC:
                return "assets://music/" + metadata.optString("poster");
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(type);

        long[] ids = new long[locationIds.size()];
        for(Long id: locationIds){
            ids[locationIds.indexOf(id)] = id;
        }

        dest.writeInt(locationIds.size());
        dest.writeLongArray(ids);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(summary);
        dest.writeString(metadata.toString());
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
