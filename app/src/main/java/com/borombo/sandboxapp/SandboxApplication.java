package com.borombo.sandboxapp;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Erwan on 21/04/2017.
 */

public class SandboxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/AvenirNextLTPro-Cn.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()
        );
    }

}
