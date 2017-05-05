package com.borombo.sandboxapp.common.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.butterknife.ButterKnifeMainActivity;
import com.borombo.sandboxapp.calligraphy.CalligraphyMainActivity;
import com.borombo.sandboxapp.common.HomeContentAdapter;
import com.borombo.sandboxapp.common.model.Content;
import com.borombo.sandboxapp.firebase.FirebaseMainActivity;
import com.borombo.sandboxapp.glide.GlideMainActivity;
import com.borombo.sandboxapp.retrofit.activities.RetrofitMainActivity;

import java.util.ArrayList;

public class MainActivity extends CommonActivity {

    private HomeContentAdapter contentAdapter;
    private ArrayList<Content> contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActionBar(getString(R.string.app_name));

        contents = new ArrayList<>();

        contents.add(new Content(0,getString(R.string.firebase),getString(R.string.firebase_desc), R.drawable.firebase_icon, FirebaseMainActivity.class));
        contents.add(new Content(1,getString(R.string.calligraphy),getString(R.string.calligraphy_desc), R.drawable.calligraphy_icon, CalligraphyMainActivity.class));
        contents.add(new Content(2,getString(R.string.retrofit),getString(R.string.retrofit_desc), R.drawable.retrofit_icon, RetrofitMainActivity.class));
        contents.add(new Content(3,getString(R.string.butterknife),getString(R.string.butterknife_desc), R.drawable.butterknife_icon, ButterKnifeMainActivity.class));
        contents.add(new Content(4,getString(R.string.glide),getString(R.string.glide_desc), R.drawable.glide_icon, GlideMainActivity.class));

        RecyclerView contentRecyclerView = (RecyclerView) findViewById(R.id.contentRecyclerView);

        contentAdapter = new HomeContentAdapter(this.contents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contentRecyclerView.setLayoutManager(linearLayoutManager);
        contentRecyclerView.setAdapter(contentAdapter);
    }

}
