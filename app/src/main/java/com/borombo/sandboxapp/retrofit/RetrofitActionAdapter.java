package com.borombo.sandboxapp.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.sandboxapp.R;

import java.util.ArrayList;

/**
 * Created by Erwan on 22/04/2017.
 */

public class RetrofitActionAdapter extends RecyclerView.Adapter<RetrofitActionHolder> {

    private ArrayList<String> actions;

    @Override
    public RetrofitActionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.retrofit_action_row, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return new RetrofitActionHolder(view);
    }

    @Override
    public void onBindViewHolder(RetrofitActionHolder holder, int position) {
        String action = actions.get(position);
        holder.updateUI(action);
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

}
