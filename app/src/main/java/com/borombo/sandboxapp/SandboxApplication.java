package com.borombo.sandboxapp;

import android.app.Activity;
import android.app.Application;

import com.borombo.sandboxapp.config.ConfigFileManager;
import com.squareup.leakcanary.LeakCanary;

import org.solovyev.android.checkout.Billing;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Erwan on 21/04/2017.
 */

public class SandboxApplication extends Application {

    private static SandboxApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Setupt Calligraphy
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/AvenirNextLTPro-Cn.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()
        );

        // Setupt LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)){return;}
        LeakCanary.install(this);
    }

    /**
     +     * Returns an instance of {@link SandboxApplication} attached to the passed activity.
     +     */
    public static SandboxApplication get(Activity activity) {
        return (SandboxApplication) activity.getApplication();
    }

    private final Billing mBilling = new Billing(this, new Billing.DefaultConfiguration() {
        @Override
        public String getPublicKey() {
            return ConfigFileManager.getConfigValue(instance,"app_licence_key");
        }
    });

    public SandboxApplication() {
        instance = this;
    }

    public static SandboxApplication get() {
        return instance;
    }

    public Billing getBilling() {
        return mBilling;
    }

}
