package com.borombo.sandboxapp.retrofit.services;

import com.borombo.sandboxapp.retrofit.model.JSPPost;
import com.borombo.sandboxapp.retrofit.model.JSPTodo;
import com.borombo.sandboxapp.retrofit.model.JSPUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Erwan on 22/04/2017.
 */

public interface JSONPlaceholderService {

    String URL = "https://jsonplaceholder.typicode.com/";

    @GET("/posts")
    Call<List<JSPPost>> listPosts();

    @GET("/users")
    Call<List<JSPUser>> listUsers();

    @GET("/todos")
    Call<List<JSPTodo>> listTodos();
}
