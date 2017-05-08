package com.borombo.sandboxapp.firebase.cloudmessaging;

import android.content.Intent;
import android.util.Log;

import com.borombo.sandboxapp.common.activities.MainActivity;
import com.borombo.sandboxapp.config.NotificationsManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Erwan on 08/05/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final  String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            try{
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
            } catch (JSONException e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    private void sendPushNotification(JSONObject json){
        // Display the json data
        Log.e(TAG, "Notification JSON " + json.toString());
        try{
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");
            String imgUrl = data.getString("image");

            NotificationsManager notificationsManager = new NotificationsManager(getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            if (imgUrl.equals("null")){
                notificationsManager.showSmallNotification(title, message, intent);
            }else{
                notificationsManager.showBigNotification(title, message, imgUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON Exception: " + e.getMessage());
        } catch (Exception e){
            Log.e(TAG, "Exception:" + e.getMessage());
        }
    }
}
