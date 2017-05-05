package com.borombo.sandboxapp.butterknife;

import android.os.Bundle;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterKnifeMainActivity extends CommonActivity {

    @BindView(R.id.text_one)
    TextView textViewOne;

    @BindString(R.string.first_text)
    String textOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_main);

        setUpActionBar(getString(R.string.butterknife));

        ButterKnife.bind(this);

        textViewOne.setText(textOne);

    }

}
