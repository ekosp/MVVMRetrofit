package com.ekosp.mvvmretrofit.repository.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

@Entity(tableName = "counter_table")
public class Counter {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "value")
    private int value;

    public Counter(@NonNull int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public int getValue() {
        return value;
    }

    public void setValue(@NonNull int total) {
        this.value = total;
    }
}
