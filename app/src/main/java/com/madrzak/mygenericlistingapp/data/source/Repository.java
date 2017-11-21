package com.madrzak.mygenericlistingapp.data.source;

import android.arch.lifecycle.LiveData;

import com.madrzak.mygenericlistingapp.data.model.UserModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Łukasz on 09/11/2017.
 */

public interface Repository<T> {

    io.reactivex.Observable<Boolean> add(T item);

    Observable<Boolean> update(T item);

    void remove(T item);

    LiveData<List<UserModel>> getAll();

    LiveData<UserModel> getUser(int userId);


}
