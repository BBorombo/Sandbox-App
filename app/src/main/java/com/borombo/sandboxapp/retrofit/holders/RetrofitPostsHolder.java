package com.borombo.sandboxapp.retrofit.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.model.JSPPost;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitPostsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.post_id)
    TextView postId;

    @BindView(R.id.post_title)
    TextView postTitle;

    @BindView(R.id.post_body)
    TextView postBody;

    public RetrofitPostsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateUI(JSPPost post){
        this.postId.setText("#" + post.getId());
        this.postTitle.setText(post.getTitle());
        this.postBody.setText(post.getBody());
    }
}
