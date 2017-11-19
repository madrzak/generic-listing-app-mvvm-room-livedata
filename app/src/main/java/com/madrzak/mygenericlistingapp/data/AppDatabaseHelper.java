package com.madrzak.mygenericlistingapp.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import lombok.Getter;

/**
 * Created by Łukasz on 16/11/2017.
 */


public class AppDatabaseHelper {
    private static final String DB_NAME = "lukasz-secret-data";

    private static volatile AppDatabaseHelper mAppDatabaseHelper;

    @Getter
    private static AppDatabase database;

    private AppDatabaseHelper(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();
    }

    public static AppDatabaseHelper getInstance(Context context) {
        if (mAppDatabaseHelper == null) {
            synchronized (AppDatabaseHelper.class) {
                if (mAppDatabaseHelper == null) {
                    mAppDatabaseHelper = new AppDatabaseHelper(context);
                }
            }
        }
        return mAppDatabaseHelper;
    }

}
