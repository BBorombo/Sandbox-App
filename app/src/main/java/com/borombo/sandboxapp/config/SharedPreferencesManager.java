package com.borombo.sandboxapp.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Erwan on 08/05/2017.
 */

public class SharedPreferencesManager {
    private static final String SHARED_PREF_NAME = "FFCMSharedPref";
    private static final String TAG_TOKEN = "tagtoken";

    private static SharedPreferencesManager instance;
    private static Context context;

    private SharedPreferencesManager(Context context){this.context = context;}

    public static synchronized SharedPreferencesManager getInstance(Context context){
        if (instance == null){
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    public String getDeviceToken(){
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG_TOKEN, null);
    }
}
