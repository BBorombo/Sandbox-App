package com.borombo.sandboxapp.retrofit.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Erwan on 22/04/2017.
 */

public class RetrofitActionHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.actionText)
    TextView actionText;

    public RetrofitActionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateUI(String text){
        this.actionText.setText(text);
    }

}
