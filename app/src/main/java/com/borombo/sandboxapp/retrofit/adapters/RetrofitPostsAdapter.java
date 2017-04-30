package com.borombo.sandboxapp.retrofit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.holders.RetrofitPostsHolder;
import com.borombo.sandboxapp.retrofit.model.JSPPost;

import java.util.List;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitPostsAdapter extends RecyclerView.Adapter<RetrofitPostsHolder> {

    List<JSPPost> posts;

    public RetrofitPostsAdapter(List<JSPPost> posts){this.posts = posts;}

    @Override
    public RetrofitPostsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.retrofit_post_row, parent, false);
        return new RetrofitPostsHolder(view);
    }

    @Override
    public void onBindViewHolder(RetrofitPostsHolder holder, int position) {
        JSPPost post = posts.get(position);
        holder.updateUI(post);
    }

    @Override
    public int getItemCount() {return posts.size();}
}
