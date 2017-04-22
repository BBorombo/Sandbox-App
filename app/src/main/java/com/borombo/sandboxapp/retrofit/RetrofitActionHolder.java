package com.borombo.sandboxapp.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;

/**
 * Created by Erwan on 22/04/2017.
 */

public class RetrofitActionHolder extends RecyclerView.ViewHolder {

    private TextView actionText;

    public RetrofitActionHolder(View itemView) {
        super(itemView);
        this.actionText = (TextView) itemView.findViewById(R.id.actionText);
    }

    public void updateUI(String text){
        this.actionText.setText(text);
    }

}
