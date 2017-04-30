package com.borombo.sandboxapp.retrofit.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.retrofit.model.JSPUser;

/**
 * Created by Phantom on 30/04/2017.
 */

public class RetrofitUsersHolder extends RecyclerView.ViewHolder {

    private TextView userId;
    private TextView userUsername;
    private TextView userEmail;
    private TextView userPhone;
    private TextView userWebsite;

    public RetrofitUsersHolder(View itemView) {
        super(itemView);
        userId = (TextView) itemView.findViewById(R.id.user_id);
        userUsername = (TextView) itemView.findViewById(R.id.user_username);
        userEmail = (TextView) itemView.findViewById(R.id.user_email);
        userPhone = (TextView) itemView.findViewById(R.id.user_phone);
        userWebsite = (TextView) itemView.findViewById(R.id.user_website);
    }

    public void updateUI(JSPUser user){
        this.userId.setText("#" + user.getId());
        this.userUsername.setText(user.getUsername());
        this.userEmail.setText("Email : " + user.getEmail());
        this.userPhone.setText("Phone : " + user.getPhone());
        this.userWebsite.setText("Website : " + user.getWebsite());
    }

}
