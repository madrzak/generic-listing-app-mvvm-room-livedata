package com.madrzak.mygenericlistingapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.support.test.InstrumentationRegistry;

import com.madrzak.mygenericlistingapp.data.AppDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Łukasz on 19/11/2017.
 */

public class MigrationTest {

    private static final String TEST_DB_NAME = "test-db";

    @Rule
    public MigrationTestHelper testHelper = new MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            AppDatabase.class.getCanonicalName(),
            new FrameworkSQLiteOpenHelperFactory());


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testMigration1_2() throws Exception {
        // Create db in version 1
        SupportSQLiteDatabase db = testHelper.createDatabase(TEST_DB_NAME, 1);

        // Insert data

        // Prepera for next version
        db.close();

        // Re-open the database with version 2 and provide MIGRATION_1_2
        testHelper.runMigrationsAndValidate(TEST_DB_NAME, 2, true, AppDatabase.MIGRATION_1_2);

        // Validate data correct
        Assert.assertTrue(true);

    }
}
