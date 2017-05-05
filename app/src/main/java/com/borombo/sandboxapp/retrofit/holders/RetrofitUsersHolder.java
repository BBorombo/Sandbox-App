package com.borombo.sandboxapp.retrofit.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.model.JSPUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitUsersHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_id)
    TextView userId;

    @BindView(R.id.user_username)
    TextView userUsername;

    @BindView(R.id.user_email)
    TextView userEmail;

    @BindView(R.id.user_phone)
    TextView userPhone;

    @BindView(R.id.user_website)
    TextView userWebsite;

    public RetrofitUsersHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateUI(JSPUser user){
        this.userId.setText("#" + user.getId());
        this.userUsername.setText(user.getUsername());
        this.userEmail.setText("Email : " + user.getEmail());
        this.userPhone.setText("Phone : " + user.getPhone());
        this.userWebsite.setText("Website : " + user.getWebsite());
    }

}
