package com.borombo.sandboxapp.calligraphy;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.widget.TextView;

import com.borombo.sandboxapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Phantom on 21/04/2017.
 */

public class CalligraphyMainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calligraphy_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.calligraphy));

        TextView githubLink = (TextView) findViewById(R.id.githubLink);
        Linkify.addLinks(githubLink, Linkify.ALL);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
