package com.borombo.sandboxapp.firebase;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.borombo.sandboxapp.R;

public class FirebaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Firebase");
        actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this,R.color.firebase_primary)));


        ConstraintLayout authView = (ConstraintLayout) findViewById(R.id.firebaseAuthView);
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
