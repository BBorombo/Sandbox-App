package com.borombo.sandboxapp.config;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.borombo.sandboxapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Phantom on 19/04/2017.
 */

public class ConfigFileManager {
    private static final String TAG = "ConfigFileManager";

    public static String getConfigValue(Context context, String name){
        Resources resources = context.getResources();

        try {
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            return properties.getProperty(name);

        } catch (Resources.NotFoundException e){
            Log.e(TAG, "Unable to find the config file: "+ e.getMessage());
        }catch (IOException e) {
            Log.e(TAG, "Fail to open config file.");
        }
        return null;
    }
}
