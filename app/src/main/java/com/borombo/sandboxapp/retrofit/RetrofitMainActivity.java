package com.borombo.sandboxapp.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.services.JSONPlaceholderService;

import retrofit2.Retrofit;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RetrofitMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.retrofit));

        RecyclerView actionsRecyclerView = (RecyclerView) findViewById(R.id.retrofitActionRecyclerView);
        RetrofitActionAdapter adapter = new RetrofitActionAdapter();
        actionsRecyclerView.setAdapter(adapter);
        actionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSONPlaceholderService.URL)
                .build();

        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
