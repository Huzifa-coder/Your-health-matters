package com.barmej.wecare.data.Time;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class TimerUtils {

    public static String tag = "schedule";

    @TargetApi(Build.VERSION_CODES.N)
    public static void startScreenMonitor(Context context) {
        // Constrains declaration
        Constraints constraints = new Constraints.Builder()
                // Call the builder's build method build the Constraints object
                .build();

        // Create PeriodicWorkRequest to periodically sync weather data
        PeriodicWorkRequest periodicWorkRequest =
                // Make this Worker repeated after specified interval
                new PeriodicWorkRequest.Builder(TimerWorker.class, 1, TimeUnit.MINUTES)
                        // Set the constraints
                        .setConstraints(constraints)
                        .setInitialDelay(5, TimeUnit.MINUTES)
                        .addTag(tag)
                        // Call the builder's build method build the PeriodicWorkRequest
                        .build();

        // Get instance of WorkManager
        WorkManager workManager = WorkManager.getInstance(context);

        // Enqueues the periodic work request
        workManager.enqueueUniquePeriodicWork(
                "TimerWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest);

    }

    public static void cancelScreenMonitor(Context context) {
        WorkManager.getInstance(context).cancelAllWorkByTag(TimerUtils.tag);
    }

}
