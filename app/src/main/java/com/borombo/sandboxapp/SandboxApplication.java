package com.borombo.sandboxapp;

import android.app.Application;

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

    private final Billing mBilling = new Billing(this, new Billing.DefaultConfiguration() {
        @Override
        public String getPublicKey() {
            return "Your public key, don't forget abput encryption";
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
