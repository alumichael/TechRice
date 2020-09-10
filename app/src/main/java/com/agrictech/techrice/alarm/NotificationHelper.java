package com.agrictech.techrice.alarm;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.agrictech.techrice.R;


public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "FarmHelpID";
    public static final String channelName = "FarmHelp";
    private NotificationManager notificationManager;

    public NotificationHelper(Context base) {
        super( base );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName,
                NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }


    public NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }




    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Check your Farm Calendar")
                .setSmallIcon( R.drawable.ic_baseline_notify);

    }
}

