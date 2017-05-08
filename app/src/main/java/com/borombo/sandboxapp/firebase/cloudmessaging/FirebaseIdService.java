package com.borombo.sandboxapp.firebase.cloudmessaging;

import android.util.Log;

import com.borombo.sandboxapp.config.SharedPreferencesManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Erwan on 08/05/2017.
 */

public class FirebaseIdService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed TOken : " + refreshedToken);

        // Can store token on the server side but for now we will just
        // store it in the sharedPreferences
        storeToken(refreshedToken);
    }

    private void storeToken(String token){
        SharedPreferencesManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
