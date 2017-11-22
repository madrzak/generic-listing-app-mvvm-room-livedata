package com.madrzak.mygenericlistingapp.data.source;

import com.madrzak.mygenericlistingapp.data.model.UserModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Łukasz on 09/11/2017.
 */

public interface Repository<T> {

    Observable<Boolean> add(T item);

    Observable<Boolean> update(T item);

    void remove(T item);

    Flowable<List<UserModel>> getAll();

    Flowable<UserModel> getUser(int userId);


}
