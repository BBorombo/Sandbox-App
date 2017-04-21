package com.borombo.sandboxapp.common.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.calligraphy.CalligraphyMainActivity;
import com.borombo.sandboxapp.common.HomeContentAdapter;
import com.borombo.sandboxapp.common.model.Content;
import com.borombo.sandboxapp.firebase.FirebaseMainActivity;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private HomeContentAdapter contentAdapter;
    private ArrayList<Content> contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contents = new ArrayList<>();

        contents.add(new Content(0,getString(R.string.firebase),getString(R.string.firebase_desc), R.drawable.firebase_icon, FirebaseMainActivity.class));
        contents.add(new Content(1,getString(R.string.calligraphy),getString(R.string.calligraphy_desc), R.drawable.calligraphy_icon, CalligraphyMainActivity.class));

        RecyclerView contentRecyclerView = (RecyclerView) findViewById(R.id.contentRecyclerView);

        contentAdapter = new HomeContentAdapter(this.contents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contentRecyclerView.setLayoutManager(linearLayoutManager);
        contentRecyclerView.setAdapter(contentAdapter);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
