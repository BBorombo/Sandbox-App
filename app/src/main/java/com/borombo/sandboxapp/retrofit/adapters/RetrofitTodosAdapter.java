package com.borombo.sandboxapp.retrofit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.holders.RetrofitTodosHolder;
import com.borombo.sandboxapp.retrofit.model.JSPTodo;

import java.util.List;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitTodosAdapter extends RecyclerView.Adapter<RetrofitTodosHolder> {

    List<JSPTodo> todos;

    public RetrofitTodosAdapter(List<JSPTodo> todos){this.todos = todos;}

    @Override
    public RetrofitTodosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.retrofit_todo_row, parent, false);
        return new RetrofitTodosHolder(view);
    }

    @Override
    public void onBindViewHolder(RetrofitTodosHolder holder, int position) {
        JSPTodo todo = todos.get(position);
        holder.updateUI(todo);
    }

    @Override
    public int getItemCount() {return todos.size();}
}
