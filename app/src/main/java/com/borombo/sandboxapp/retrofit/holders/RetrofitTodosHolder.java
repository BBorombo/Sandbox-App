package com.borombo.sandboxapp.retrofit.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.model.JSPTodo;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitTodosHolder extends RecyclerView.ViewHolder {

    private TextView todoId;
    private TextView todoTitle;
    private TextView todoCompleted;

    public RetrofitTodosHolder(View itemView) {
        super(itemView);
        todoId = (TextView) itemView.findViewById(R.id.todo_id);
        todoTitle = (TextView) itemView.findViewById(R.id.todo_title);
        todoCompleted = (TextView) itemView.findViewById(R.id.todo_completed);
    }

    public void updateUI(JSPTodo todo){
        this.todoId.setText("#" + todo.getId());
        this.todoTitle.setText(todo.getTitle());
        this.todoCompleted.setText("Completed : " + todo.isCompleted());
    }
}
