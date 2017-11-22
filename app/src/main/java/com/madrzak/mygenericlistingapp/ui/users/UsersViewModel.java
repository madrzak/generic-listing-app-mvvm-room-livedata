package com.madrzak.mygenericlistingapp.ui.users;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.MutableLiveData;

import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;
import com.madrzak.mygenericlistingapp.data.model.UserModel;
import com.madrzak.mygenericlistingapp.data.source.UsersRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

/**
 * Created by Łukasz on 18/11/2017.
 */

public class UsersViewModel extends AndroidViewModel {

    private UsersRepository usersRepository;

    @Getter
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public UsersViewModel(Application application) {
        super(application);

        if (usersRepository == null) {
            usersRepository = UsersRepository.getInstance(AppDatabaseHelper.getInstance(application).getDatabase().userDao());
        }
    }

    public LiveData<List<UserModel>> getUsers() {

        return LiveDataReactiveStreams.fromPublisher(
                usersRepository.getAll()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }
}