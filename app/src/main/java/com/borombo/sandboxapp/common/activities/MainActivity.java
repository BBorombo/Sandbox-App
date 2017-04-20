package com.borombo.sandboxapp.common.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.HomeContentAdapter;
import com.borombo.sandboxapp.common.model.Content;
import com.borombo.sandboxapp.firebase.FirebaseMainActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private HomeContentAdapter contentAdapter;
    private ArrayList<Content> contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contents = new ArrayList<>();

        contents.add(new Content(0,"Firebase","Many Authentcation, Database storage...", R.drawable.firebase_icon, FirebaseMainActivity.class));

        RecyclerView contentRecyclerView = (RecyclerView) findViewById(R.id.contentRecyclerView);

        contentAdapter = new HomeContentAdapter(this.contents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contentRecyclerView.setLayoutManager(linearLayoutManager);
        contentRecyclerView.setAdapter(contentAdapter);
    }
}
