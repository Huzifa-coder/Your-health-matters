package com.barmej.wecare.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.barmej.wecare.data.Limit;
import com.barmej.wecare.data.database.dao.LimitDao;
@Database(entities = {Limit.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Instance of this class for Singleton
     */
    private static final Object LOCK = new Object();

    /**
     * Database file name
     */
    private static final String DATABASE_NAME = "Limit_db";

    /**
     * Instance of this class for Singleton
     */
    private static AppDatabase sInstance;

    /**
     * Method used to get an instance of AppDatabase class
     *
     * @param context Context to use for Room initializations
     * @return an instance of AppDatabase class
     */
    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME
                ).build();
            }
        }
        return sInstance;
    }

    public abstract LimitDao getLimitDao();

}
