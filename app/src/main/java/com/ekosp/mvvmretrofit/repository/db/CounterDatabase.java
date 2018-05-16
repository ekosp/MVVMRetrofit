package com.ekosp.mvvmretrofit.repository.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

@Database(entities = {Counter.class}, version = 2)
public abstract class CounterDatabase extends RoomDatabase {

    public abstract CounterDAO counterDao();

    private static CounterDatabase INSTANCE;

    public static CounterDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CounterDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CounterDatabase.class, "counter_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
       */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CounterDAO mDao;

        PopulateDbAsync(CounterDatabase db) {
            mDao = db.counterDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();
            mDao.insert(new Counter(1));

            return null;
        }
    }

}
