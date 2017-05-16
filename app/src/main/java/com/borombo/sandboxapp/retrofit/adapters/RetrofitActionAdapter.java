package com.borombo.sandboxapp.retrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.activities.RetrofitGetActionResultActivity;
import com.borombo.sandboxapp.retrofit.holders.RetrofitActionHolder;

import java.util.ArrayList;

/**
 * Created by Erwan on 22/04/2017.
 */

public class RetrofitActionAdapter extends RecyclerView.Adapter<RetrofitActionHolder> {

    private ArrayList<String> actions = new ArrayList<>();
    public static final String EXTRA_NAME = "Action";

    public RetrofitActionAdapter(){
        actions.add("/GET : Posts");
        actions.add("/GET : Todos");
        actions.add("/GET : Users");
    }

    @Override
    public RetrofitActionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.retrofit_action_row, parent, false);

        final Context context = view.getContext();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v.findViewById(R.id.actionText);
                Intent intent = new Intent(context, RetrofitGetActionResultActivity.class);
                intent.putExtra(RetrofitActionAdapter.EXTRA_NAME, textView.getText().toString());
                context.startActivity(intent);
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
