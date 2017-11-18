package com.madrzak.mygenericlistingapp.data.source;

import android.arch.lifecycle.LiveData;

import com.madrzak.mygenericlistingapp.data.dao.UserDao;
import com.madrzak.mygenericlistingapp.data.model.UserModel;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Łukasz on 04/11/2017.
 */

public class UsersRepository implements Repository<UserModel> {

    private volatile static UsersRepository INSTANCE = null;

    private final UserDao userDao;

    public static UsersRepository getInstance(UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (UsersRepository.class) {
                INSTANCE = new UsersRepository(userDao);
            }
        }
        return INSTANCE;
    }

    public UsersRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Observable<Boolean> add(final UserModel item) {
        return Observable.fromCallable(() -> {
            userDao.insertAll(item);
            return true;
        });
    }

    @Override
    public void update(UserModel item) {

    }

    @Override
    public void remove(UserModel item) {

    }

    @Override
    public LiveData<List<UserModel>> getAll() {
        return userDao.getAll();
    }


}
