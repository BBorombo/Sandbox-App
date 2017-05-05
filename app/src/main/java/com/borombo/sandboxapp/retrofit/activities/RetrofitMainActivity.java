package com.borombo.sandboxapp.retrofit.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitActionAdapter;

public class RetrofitMainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main);

        setUpActionBar(getString(R.string.retrofit));

        RecyclerView actionsRecyclerView = (RecyclerView) findViewById(R.id.retrofitActionRecyclerView);
        RetrofitActionAdapter adapter = new RetrofitActionAdapter();
        actionsRecyclerView.setAdapter(adapter);
        actionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView apiLink = (TextView) findViewById(R.id.apiLink);
        Linkify.addLinks(apiLink, Linkify.ALL);

    }

}
