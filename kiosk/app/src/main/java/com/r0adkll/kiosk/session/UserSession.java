package com.r0adkll.kiosk.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.r0adkll.deadskunk.utils.SecurePreferences;

import java.text.SimpleDateFormat;

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

    /* Preference Store Instances */
    private SecurePreferences mSecPrefs;
    private SharedPreferences mGenPrefs;
    private SharedPreferences mDefaultPrefs;


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


}
