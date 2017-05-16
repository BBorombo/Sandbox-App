package com.borombo.sandboxapp.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebaseMainActivity extends CommonActivity {

    @BindView(R.id.firebaseAuthView)
    ConstraintLayout authView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);
        ButterKnife.bind(this);

        setUpActionBar(getString(R.string.firebase), ContextCompat.getColor(this,R.color.firebase_dark));

        authView.setClickable(true);
        authView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirebaseMainActivity.this, AuthMainActivity.class);
                startActivity(intent);
            }
        });
    }

}
