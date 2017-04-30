package com.borombo.sandboxapp.retrofit.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.model.JSPPost;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitPostsHolder extends RecyclerView.ViewHolder {

    private TextView postId;
    private TextView postTitle;
    private TextView postBody;

    public RetrofitPostsHolder(View itemView) {
        super(itemView);
        postId = (TextView) itemView.findViewById(R.id.post_id);
        postTitle = (TextView) itemView.findViewById(R.id.post_title);
        postBody = (TextView) itemView.findViewById(R.id.post_body);
    }

    public void updateUI(JSPPost post){
        this.postId.setText("#" + post.getId());
        this.postTitle.setText(post.getTitle());
        this.postBody.setText(post.getBody());
    }
}
