package com.borombo.sandboxapp.calligraphy;

import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;

/**
 * Created by Phantom on 21/04/2017.
 */

public class CalligraphyMainActivity extends CommonActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calligraphy_main);

        setUpActionBar(getString(R.string.calligraphy));

        TextView githubLink = (TextView) findViewById(R.id.githubLink);
        Linkify.addLinks(githubLink, Linkify.ALL);
    }
}
