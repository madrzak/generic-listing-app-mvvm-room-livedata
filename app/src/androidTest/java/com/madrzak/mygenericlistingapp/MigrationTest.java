package com.madrzak.mygenericlistingapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.madrzak.mygenericlistingapp.data.AppDatabase;
import com.madrzak.mygenericlistingapp.data.model.UserModel;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Łukasz on 19/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MigrationTest {

    private static final String TEST_DB_NAME = "test-db";

    @Rule
    public MigrationTestHelper mMigrationTestHelper = new MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            AppDatabase.class.getCanonicalName(),
            new FrameworkSQLiteOpenHelperFactory());


    @Test
    public void testMigration1_2() throws Exception {
        // Create db in version 1
        SupportSQLiteDatabase db = mMigrationTestHelper.createDatabase(TEST_DB_NAME, 1);

        UserModel user = new UserModel();
        user.setName("jonny");
        user.setSurname("english");

        // Insert data
        String sql = "INSERT INTO " + UserModel.TABLE_NAME
                + " (" + UserModel.COL_NAME + ", " + UserModel.COL_SURNAME + ")"
                + " VALUES ('" + user.getName() + "', '" + user.getSurname() + "') ";

        db.execSQL(sql);

        // Prepere for next version
        db.close();

        // Re-open the database with version 2 and provide MIGRATION_1_2
        mMigrationTestHelper.runMigrationsAndValidate(TEST_DB_NAME, 2, true, AppDatabase.MIGRATION_1_2);

        // Validate data correct
        List<UserModel> list = getValue(getMigratedRoomDatabase().userDao().getAll());
        Assert.assertEquals(1, list.size());

        UserModel newUser = list.get(0);
        Assert.assertEquals(user.getName(), newUser.getName());
        Assert.assertEquals(user.getSurname(), newUser.getSurname());
        Assert.assertNull(newUser.getDateCreated());
        
    }

    private AppDatabase getMigratedRoomDatabase() {
        AppDatabase database = Room.databaseBuilder(InstrumentationRegistry.getTargetContext(),
                AppDatabase.class, TEST_DB_NAME)
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();
        // close the database and release any stream resources when the test finishes
        mMigrationTestHelper.closeWhenFinished(database);
        return database;
    }

    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}
