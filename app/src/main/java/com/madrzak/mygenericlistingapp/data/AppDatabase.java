package com.madrzak.mygenericlistingapp.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.madrzak.mygenericlistingapp.data.dao.UserDao;
import com.madrzak.mygenericlistingapp.data.model.UserModel;


/**
 * Created by Łukasz on 04/11/2017.
 */

@Database(
        version = 2,
        entities = {UserModel.class}
)
@TypeConverters(
        {Converters.class}
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();


    
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            String sql = "ALTER TABLE " + UserModel.TABLE_NAME
                    + " ADD COLUMN " + UserModel.COL_DATE_CREATED + " TEXT";
            database.execSQL(sql);
        }
    };

}
