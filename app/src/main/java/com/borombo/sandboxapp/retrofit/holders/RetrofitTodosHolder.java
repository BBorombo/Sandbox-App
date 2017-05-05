package com.borombo.sandboxapp.retrofit.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.model.JSPTodo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitTodosHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.todo_id)
    TextView todoId;
    @BindView(R.id.todo_title)
    TextView todoTitle;
    @BindView(R.id.todo_completed)
    TextView todoCompleted;

    public RetrofitTodosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateUI(JSPTodo todo){
        this.todoId.setText("#" + todo.getId());
        this.todoTitle.setText(todo.getTitle());
        this.todoCompleted.setText("Completed : " + todo.isCompleted());
    }
}
