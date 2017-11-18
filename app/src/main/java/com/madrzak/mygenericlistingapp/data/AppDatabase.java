package com.madrzak.mygenericlistingapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.madrzak.mygenericlistingapp.data.dao.UserDao;
import com.madrzak.mygenericlistingapp.data.model.UserModel;

/**
 * Created by Łukasz on 04/11/2017.
 */

@Database(version = 1, entities = {UserModel.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
