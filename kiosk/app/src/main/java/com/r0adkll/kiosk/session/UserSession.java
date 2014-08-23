package com.r0adkll.kiosk.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.estimote.sdk.Beacon;
import com.google.gson.Gson;
import com.r0adkll.deadskunk.utils.SecurePreferences;
import com.r0adkll.kiosk.R;
import com.r0adkll.kiosk.session.model.Content;
import com.r0adkll.kiosk.session.model.Location;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by r0adkll on 8/23/14.
 */
public class UserSession {

    /**********************************************************
     *
     * Singleton Interface
     *
     */

    private static UserSession _instance;

    /**
     * Get the current Singleton instance of the UserSession
     * @return      the session instance
     */
    public static UserSession getSession(){
        if(_instance == null) _instance = new UserSession();
        return _instance;
    }

    /**********************************************************
     *
     * Constants
     *
     */

    private static final String PREFERENCE_SECURE_NAME = "CombinePreferences.secure";
    public static final String PREFERENCE_GENERIC_NAME = "CombinePreferences.prefs";

    private static final String SAUCE = "s7ArJnwbo2NaLSoIMhxHrzZzjWQBrPJPElii3y925E0=";
    private static final String FLAVOR = "dkjaieurkldadkujiuejfkdioixkdkdksid";

    /**********************************************************
     *
     * Variables
     *
     */

    public static Gson GSON = new Gson();

    /* Preference Store Instances */
    private SecurePreferences mSecPrefs;
    private SharedPreferences mGenPrefs;
    private SharedPreferences mDefaultPrefs;

    private HashMap<Long, Location> mLocations = new HashMap<>();
    private HashMap<Long, Content> mContent = new HashMap<>();


    /**********************************************************
     *
     * Initialization Methods
     *
     */

    /**
     * Initialize this UserSession
     *
     * @param ctx       the context to initalize with
     */
    public void initialize(Context ctx){
        // Initialize the Preferences
        mSecPrefs = new SecurePreferences(ctx, PREFERENCE_SECURE_NAME, SAUCE, FLAVOR, true);
        mGenPrefs = ctx.getSharedPreferences(PREFERENCE_GENERIC_NAME, Context.MODE_PRIVATE);
        mDefaultPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    /**********************************************************
     *
     * Data Loading Methods
     *
     */

    private void loadKioskData(Context ctx){

        // Load Kiosk JSON data from raw directory
        InputStream is = ctx.getResources().openRawResource(R.raw.kiosk_data);
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(is, writer);
            String rawJson = writer.toString();

            JSONObject json = new JSONObject(rawJson);

            // Begin parsing kiosk data
            JSONArray locations = json.optJSONArray("locations");
            int N = locations.length();
            for(int i=0; i<N; i++){
                JSONObject location = locations.optJSONObject(i);
                Location newLocation = new Location(location);
                mLocations.put(newLocation.id, newLocation);
            }

            // parse content
            JSONArray contents = json.optJSONArray("content");
            int N2 = contents.length();
            for(int i=0; i<N2; i++){
                JSONObject content = contents.optJSONObject(i);
                Content newContent = new Content(content);
                mContent.put(newContent.id, newContent);
            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Make request to API to get the Location identifier for a given beacon
     *
     * @param beacon        the beacon to request with
     * @param handler       the callback response handler
     */
    public void getLocationForBeacon(Beacon beacon, APIHandler<Location> handler){

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> bcon = new HashMap<>();
        bcon.put("mac", beacon.getMacAddress());
        bcon.put("major", beacon.getMajor());
        bcon.put("minor", beacon.getMinor());
        params.put("beacon", bcon);

        String jsonRequest = GSON.toJson(params);

        // Make Request

    }


}
