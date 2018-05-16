package com.ekosp.mvvmretrofit.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.ekosp.mvvmretrofit.repository.db.Counter;
import com.ekosp.mvvmretrofit.repository.db.CounterDAO;
import com.ekosp.mvvmretrofit.repository.db.CounterDatabase;

import java.util.Date;
import java.util.List;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public class DataRepository {

    private static MutableLiveData<Long> data = new MutableLiveData<Long>();

    private CounterDAO mCounterDao;
    private LiveData<Counter> mCounter;

    // create repository
    public DataRepository(Application application) {
        CounterDatabase db = CounterDatabase.getDatabase(application);
        mCounterDao = db.counterDao();
        mCounter = mCounterDao.getTotalDigit();
    }

    public LiveData<Counter> getTotalDigit() {
        return mCounter;
    }

    public void insert (Counter counter) {
        new insertAsyncTask(mCounterDao).execute(counter);
    }

    private static class insertAsyncTask extends AsyncTask<Counter, Void, Void> {
        private CounterDAO mAsyncCounterDao;
        insertAsyncTask(CounterDAO dao) {
            mAsyncCounterDao = dao;
        }

        @Override
        protected Void doInBackground(final Counter... params) {
            mAsyncCounterDao.insert(params[0]);
            return null;
        }
    }

    //sets latest time to LiveData
    public static LiveData<Long> getData(){
        data.setValue(new Date().getTime());
        return data;
    }
}