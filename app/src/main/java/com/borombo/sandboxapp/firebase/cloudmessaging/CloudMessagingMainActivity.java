package com.borombo.sandboxapp.firebase.cloudmessaging;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.borombo.sandboxapp.config.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CloudMessagingMainActivity extends CommonActivity {

    @BindView(R.id.deviceToken)
    TextView deviceTokenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_messaging_main);
        setUpActionBar(getString(R.string.firebase_cm), ContextCompat.getColor(this, R.color.firebase_dark));
        ButterKnife.bind(this);

        String token = SharedPreferencesManager.getInstance(this).getDeviceToken();

        if (token != null){
            deviceTokenView.setText(token);
            Log.d("TOKEN ", token);
        }else{
            deviceTokenView.setText("Token not generated");
        }

    }

}
