package com.borombo.sandboxapp.retrofit.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitActionAdapter;

import butterknife.BindView;

public class RetrofitMainActivity extends CommonActivity {

    @BindView(R.id.retrofitActionRecyclerView)
    RecyclerView actionsRecyclerView;

    @BindView(R.id.apiLink)
    TextView apiLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main);

        setUpActionBar(getString(R.string.retrofit));

        RetrofitActionAdapter adapter = new RetrofitActionAdapter();
        actionsRecyclerView.setAdapter(adapter);
        actionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Linkify.addLinks(apiLink, Linkify.ALL);
    }

}
