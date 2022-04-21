package com.barmej.wecare.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.barmej.wecare.data.Limit;
import com.barmej.wecare.data.database.AppDatabase;
import com.barmej.wecare.utiles.AppExecutor;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static AppExecutor mAppExecutor;
    private static AppDatabase mAppDatabase;


    public MainViewModel(@NonNull Application application){
        super(application);
        mAppDatabase = AppDatabase.getInstance(application.getApplicationContext());

        mAppExecutor = AppExecutor.getInstance();

    }

    public void addLimit(Limit limit){
        mAppExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.getLimitDao().addLimit(limit);
            }
        });
    }

    public void updateLimit(Limit limit){
        mAppExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.getLimitDao().updateLimit(limit);
            }
        });
    }

    public LiveData<List<Limit>> getLimits(){
        return mAppDatabase.getLimitDao().getLimits();
    }


    public LiveData<Limit> getLimit(String date){
        return mAppDatabase.getLimitDao().getLimit(date);
    }

}
