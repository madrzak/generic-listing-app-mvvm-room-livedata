package com.madrzak.mygenericlistingapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;
import com.madrzak.mygenericlistingapp.data.AppDatabase;
import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;
import com.madrzak.mygenericlistingapp.data.model.UserModel;

import timber.log.Timber;

/**
 * Created by Łukasz on 04/11/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Stetho.initializeWithDefaults(this);


        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lukasz-secret-data").build();

        final UserModel user = new UserModel();
        user.setName("lukasz");
        user.setSurname("Madrzak");

        new Thread(() ->
                db.userDao().insertAll(user)
        ).start();


        AppDatabaseHelper.init(db);

    }
}
