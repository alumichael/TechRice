package com.agrictech.techrice.alarm;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.activities.Dashboard;


public class NotificationPublisher extends BroadcastReceiver {

    String message;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper (context);
        PendingIntent pendingIntent;
        if(intent!=null){
            message = intent.getDataString();
        }



        intent = new Intent(context, Dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);



        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        nb.setContentIntent(pendingIntent);
        nb.setContentText(message);
        nb.setAutoCancel(true);
        notificationHelper.getManager().notify(1, nb.build());
        Notification notification = nb.build();

        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_SOUND;
    }
}
