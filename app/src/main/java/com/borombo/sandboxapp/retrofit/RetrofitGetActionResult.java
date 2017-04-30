package com.borombo.sandboxapp.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitActionAdapter;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitPostsAdapter;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitTodosAdapter;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitUsersAdpater;
import com.borombo.sandboxapp.retrofit.model.JSPPost;
import com.borombo.sandboxapp.retrofit.model.JSPTodo;
import com.borombo.sandboxapp.retrofit.model.JSPUser;
import com.borombo.sandboxapp.retrofit.services.JSONPlaceholderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitGetActionResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_get_action_result);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.actionResultRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSONPlaceholderService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);

        String action = getIntent().getStringExtra(RetrofitActionAdapter.EXTRA_NAME);

        switch (action){
            case "/GET : Posts":
                Call<List<JSPPost>> posts = service.listPosts();
                posts.enqueue(new Callback<List<JSPPost>>() {
                    @Override
                    public void onResponse(Call<List<JSPPost>> call, Response<List<JSPPost>> response) {
                        recyclerView.setAdapter(new RetrofitPostsAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<JSPPost>> call, Throwable t) {

                    }
                });
                break;

            case "/GET : Todos":
                Call<List<JSPTodo>> todos = service.listTodos();
                todos.enqueue(new Callback<List<JSPTodo>>() {
                    @Override
                    public void onResponse(Call<List<JSPTodo>> call, Response<List<JSPTodo>> response) {
                        recyclerView.setAdapter(new RetrofitTodosAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<JSPTodo>> call, Throwable t) {

                    }
                });
                break;

            case "/GET : Users":
                Call<List<JSPUser>> users = service.listUsers();
                users.enqueue(new Callback<List<JSPUser>>() {
                    @Override
                    public void onResponse(Call<List<JSPUser>> call, Response<List<JSPUser>> response) {
                        recyclerView.setAdapter(new RetrofitUsersAdpater(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<JSPUser>> call, Throwable t) {

                    }
                });
                break;
            default:

                break;
        }

    }
}
