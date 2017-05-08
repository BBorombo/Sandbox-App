package com.borombo.sandboxapp.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.borombo.sandboxapp.firebase.authentication.AuthMainActivity;
import com.borombo.sandboxapp.firebase.cloudmessaging.CloudMessagingMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirebaseMainActivity extends CommonActivity {

    @BindView(R.id.firebaseAuthView)
    ConstraintLayout authView;

    @BindView(R.id.firebaseDatabaseView)
    ConstraintLayout databaseView;

    @BindView(R.id.firebaseCloudMessagingView)
    ConstraintLayout cloudMessagingView;

    @BindView(R.id.firebaseStorageView)
    ConstraintLayout storageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);
        ButterKnife.bind(this);

        setUpActionBar(getString(R.string.firebase), ContextCompat.getColor(this,R.color.firebase_dark));

        authView.setClickable(true);
        databaseView.setClickable(true);
        cloudMessagingView.setClickable(true);
        storageView.setClickable(true);
    }

    @OnClick(R.id.firebaseCloudMessagingView)
    public void clickCMView(){
        Intent intent = new Intent(FirebaseMainActivity.this, CloudMessagingMainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.firebaseAuthView)
    public void clickAuthView(){
        Intent intent = new Intent(FirebaseMainActivity.this, AuthMainActivity.class);
        startActivity(intent);
    }

}
