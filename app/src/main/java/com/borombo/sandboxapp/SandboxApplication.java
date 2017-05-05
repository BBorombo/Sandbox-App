package com.borombo.sandboxapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

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
        if (LeakCanary.isInAnalyzerProcess(this)){return;}
        LeakCanary.install(this);
    }

}
