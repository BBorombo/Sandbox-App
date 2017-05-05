package com.borombo.sandboxapp.common.activities;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * Created by Erwan on 05/05/2017.
 */

public class CommonActivity extends AppCompatActivity {

    protected ActionBar actionBar;
    protected CalligraphyTypefaceSpan typefaceSpan;
    protected SpannableString titleActionBar;

    protected void setUpActionBar(String title, int color){
        actionBar = getSupportActionBar();
        typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(getAssets(), "fonts/AvenirNextLTPro-Demi.otf"));
        titleActionBar = new SpannableString(title);
        titleActionBar.setSpan(typefaceSpan,0,titleActionBar.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setTitle(titleActionBar);
        actionBar.setBackgroundDrawable(new ColorDrawable(color));
    }

    protected void setUpActionBar(String title){
        actionBar = getSupportActionBar();
        typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(getAssets(), "fonts/AvenirNextLTPro-Demi.otf"));
        titleActionBar = new SpannableString(title);
        titleActionBar.setSpan(typefaceSpan,0,titleActionBar.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setTitle(titleActionBar);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
