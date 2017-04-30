package com.borombo.sandboxapp.retrofit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.holders.RetrofitUsersHolder;
import com.borombo.sandboxapp.retrofit.model.JSPUser;

import java.util.List;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitUsersAdpater extends RecyclerView.Adapter<RetrofitUsersHolder> {

    List<JSPUser> users;

    public RetrofitUsersAdpater(List<JSPUser> users){this.users = users;}

    @Override
    public RetrofitUsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.retrofit_user_row, parent, false);
        return new RetrofitUsersHolder(view);
    }

    @Override
    public void onBindViewHolder(RetrofitUsersHolder holder, int position) {
        JSPUser user = users.get(position);
        holder.updateUI(user);
    }

    @Override
    public int getItemCount() {return users.size();}
}
