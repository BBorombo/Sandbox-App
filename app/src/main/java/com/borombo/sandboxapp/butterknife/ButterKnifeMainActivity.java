package com.borombo.sandboxapp.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.borombo.sandboxapp.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterKnifeMainActivity extends AppCompatActivity {

    @BindView(R.id.text_one)
    TextView textViewOne;

    @BindString(R.string.first_text)
    String textOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_main);
        ButterKnife.bind(this);

        textViewOne.setText(textOne);

    }
}
