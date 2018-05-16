package com.ekosp.mvvmretrofit.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ekosp.mvvmretrofit.repository.DataRepository;
import com.ekosp.mvvmretrofit.repository.db.Counter;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public class CounterViewModel extends AndroidViewModel {

    private DataRepository mRepository;
    private LiveData<Counter> mCounter;

    public CounterViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DataRepository(application);
        mCounter = mRepository.getTotalDigit();
    }

    public LiveData<Counter> getCounter() {
        return mCounter;
    }

    public void insert(Counter counter) {
        mRepository.insert(counter);
    }
}