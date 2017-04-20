package com.borombo.sandboxapp.common;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.model.Content;

/**
 * Created by Phantom on 20/04/2017.
 */

public class HomeContentHolder extends RecyclerView.ViewHolder {

    private TextView contentName;
    private TextView contentDescription;
    private ImageView contentIcon;

    public HomeContentHolder(View itemView) {
        super(itemView);

        contentName = (TextView) itemView.findViewById(R.id.contentName);
        contentDescription = (TextView) itemView.findViewById(R.id.contentDescription);
        contentIcon = (ImageView) itemView.findViewById(R.id.contentIcon);

    }

    public void updateUI(Content content, Context context){

        contentName.setText(content.getName());
        contentDescription.setText(content.getDescription());
        contentIcon.setImageDrawable(ContextCompat.getDrawable(context,content.getIcon()));

    }

}
