package com.barmej.wecare.data.database.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.barmej.wecare.data.Limit;

import java.util.List;

@Dao
public interface LimitDao {

    @Query("SELECT * FROM `Limit`")
    LiveData<List<Limit>> getLimits();

    @Query("SELECT * FROM `Limit` where data =:data")
    LiveData<Limit> getLimit(String data);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long addLimit(Limit limit);

    @Update(onConflict = REPLACE)
    void updateLimit(Limit limit);

}
