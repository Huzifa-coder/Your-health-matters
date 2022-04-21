package com.barmej.wecare.utiles;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.barmej.wecare.R;
import com.barmej.wecare.ui.MainActivity;

public class NotificationUtils {

    private static final String Limit_STATUS_CHANNEL_ID = "Limit Status";

    private static final int Limit_NOTIFICATION_ID = 1;


    /**
     * Create a new notification channel for weather status update notifications
     *
     * @param context Context to be used to get NotificationManager from system services
     */
    public static void createWeatherStatusNotificationChannel(Context context) {
        /*
         * Create the NotificationChannel, but only on API 26+ because the NotificationChannel class
         * is new and available only from Android 8 (API 26) and above.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Define channel name and description
            CharSequence name = context.getString(R.string.limit_notification_channel_name);
            String description = context.getString(R.string.limit_notification_channel_description);
            // Create NotificationChannel object and set channel description
            NotificationChannel channel = new NotificationChannel(Limit_STATUS_CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public static void showTimerLimitNotification(Context context) {

        // Notification Title
        String notificationTitle = context.getString(R.string.app_name);

        // Notification Text
        String notificationText = context.getString(
                R.string.times_notified_today);

        NotificationCompat.Builder notificationBuilder
                = new NotificationCompat.Builder(context, Limit_STATUS_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        // This Intent will be triggered when the user clicks the notification
        Intent intent = new Intent(context, MainActivity.class);

        // Create a new TaskStackBuilder
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);

        // Add the intent to the task stack builder with parent stack
        taskStackBuilder.addNextIntentWithParentStack(intent);

        // Create a pending intent to be used by the notification to fire the original intent
        PendingIntent pendingIntent = taskStackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set notification intent to be called when the user click on the notification
        notificationBuilder.setContentIntent(pendingIntent);

        // Create notification object using builder's build method
        Notification notification = notificationBuilder.build();

        // Get instance of NotificationManager
        NotificationManager notificationManager
                = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Show the notification, the id can be used to cancel or update the notification later
        assert notificationManager != null;
        notificationManager.notify(Limit_NOTIFICATION_ID, notification);


    }
}
