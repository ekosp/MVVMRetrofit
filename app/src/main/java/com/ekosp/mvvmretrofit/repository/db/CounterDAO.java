package com.ekosp.mvvmretrofit.repository.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

@Dao
public interface CounterDAO {

    @Insert
    void insert(Counter counter);

    @Query("DELETE FROM counter_table")
    void deleteAll();

    @Query("SELECT * from counter_table WHERE id = (SELECT MAX (id) FROM counter_table) ")
    LiveData<Counter> getTotalDigit();

}
