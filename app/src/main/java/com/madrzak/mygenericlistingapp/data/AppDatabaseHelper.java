package com.madrzak.mygenericlistingapp.data;

import lombok.Getter;

/**
 * Created by Łukasz on 16/11/2017.
 */


public class AppDatabaseHelper {

    private static volatile AppDatabaseHelper mAppDatabaseHelper;

    @Getter
    private final AppDatabase database;

    private AppDatabaseHelper(AppDatabase appDatabase) {
        this.database = appDatabase;
    }

    public static AppDatabaseHelper init(AppDatabase appDatabase) {
        if (mAppDatabaseHelper == null) {
            mAppDatabaseHelper = new AppDatabaseHelper(appDatabase);
        }
        return mAppDatabaseHelper;
    }

    public static AppDatabaseHelper getInstance() {
        return mAppDatabaseHelper;
    }

}
