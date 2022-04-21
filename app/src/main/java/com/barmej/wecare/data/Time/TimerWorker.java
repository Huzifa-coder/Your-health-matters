package com.barmej.wecare.data.Time;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.barmej.wecare.data.Limit;
import com.barmej.wecare.data.database.AppDatabase;
import com.barmej.wecare.utiles.AppExecutor;
import com.barmej.wecare.utiles.NotificationUtils;
import com.barmej.wecare.utiles.Utiles;

public class TimerWorker extends Worker {

    public TimerWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        NotificationUtils.showTimerLimitNotification(getApplicationContext());
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                LiveData<Limit> limitLiveData = AppDatabase.getInstance(getApplicationContext()).getLimitDao().getLimit(Utiles.toString(System.currentTimeMillis()));
                AppExecutor.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        limitLiveData.observeForever(new Observer<Limit>() {
                            @Override
                            public void onChanged(Limit limit) {
                                limit.setCounter(limit.getCounter() + 1);
                                AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        AppDatabase.getInstance(getApplicationContext()).getLimitDao().updateLimit(limit);
                                    }
                                });
                                limitLiveData.removeObserver(this);
                            }
                        });

                    }
                });
            }
        });

        return Result.success();
    }
}
