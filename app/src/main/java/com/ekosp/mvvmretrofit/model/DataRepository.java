package com.ekosp.mvvmretrofit.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.Date;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public class DataRepository {

    private static MutableLiveData<Long> data = new MutableLiveData<Long>();

    //sets latest time to LiveData
    public static LiveData<Long> getData(){
        data.setValue(new Date().getTime());
        return data;
    }
}