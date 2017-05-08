package com.borombo.sandboxapp.config;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.borombo.sandboxapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Erwan on 08/05/2017.
 */

public class NotificationsManager {

    public  static final  int ID_BIG_NOTIFICATION = 234;
    public  static final  int ID_SMALL_NOTIFICATION = 235;

    private Context context;

    public NotificationsManager(Context context) {this.context = context;}

    public void showBigNotification(String title, String message, String url, Intent intent){
        // TODO : Implement Big Notification with image
    }

    public void showSmallNotification(String title, String message, Intent intent){
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this.context,
                                                ID_SMALL_NOTIFICATION,
                                                intent,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher).setTicker(title).setWhen(0)
                            .setAutoCancel(true)
                            .setContentIntent(resultPendingIntent)
                            .setContentTitle(title)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), R.mipmap.ic_launcher))
                            .setContentText(message)
                            .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ID_SMALL_NOTIFICATION, notification);
    }

    private Bitmap getBitmapFromUrl(String imgUrl){
        try{
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
