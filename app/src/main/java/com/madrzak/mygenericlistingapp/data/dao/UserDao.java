package com.madrzak.mygenericlistingapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.madrzak.mygenericlistingapp.data.model.UserModel;

import java.util.List;

/**
 * Created by Łukasz on 04/11/2017.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM USERS")
    LiveData<List<UserModel>> getAll();

    @Query("SELECT * FROM USERS WHERE uid IN (:userIds)")
    List<UserModel> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM USERS WHERE name LIKE :first AND "
            + "surname LIKE :last LIMIT 1")
    UserModel findByName(String first, String last);

    @Insert
    void insertAll(UserModel... users);

    @Delete
    void delete(UserModel user);

}
