package com.madrzak.mygenericlistingapp.data.source;

import android.arch.lifecycle.LiveData;

import com.madrzak.mygenericlistingapp.data.model.UserModel;

import java.util.List;

/**
 * Created by Łukasz on 09/11/2017.
 */

public interface Repository<T> {

    io.reactivex.Observable<Boolean> add(T item);

    void update(T item);

    void remove(T item);

    LiveData<List<UserModel>> getAll();


}
