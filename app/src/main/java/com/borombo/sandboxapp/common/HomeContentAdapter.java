package com.borombo.sandboxapp.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.model.Content;

import java.util.ArrayList;

/**
 * Created by Phantom on 20/04/2017.
 */

public class HomeContentAdapter extends RecyclerView.Adapter<HomeContentHolder> {

    private ArrayList<Content> contents;

    public HomeContentAdapter(ArrayList<Content> contents) {this.contents = contents;}

    @Override
    public HomeContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_content_row , parent, false);
        return new HomeContentHolder(contentView );
    }

    @Override
    public void onBindViewHolder(HomeContentHolder holder, int position) {
        final Content profile = contents.get(position);
        final Context context = holder.itemView.getContext();

        holder.updateUI(profile);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {return contents.size();}

}
