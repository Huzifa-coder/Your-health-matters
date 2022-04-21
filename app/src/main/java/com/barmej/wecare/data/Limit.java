package com.barmej.wecare.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "limit")
public class Limit {

    @PrimaryKey
    @NonNull
    private String data;
    private int counter;

    public Limit(String data) {
        this.data = data;
        counter =0;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
