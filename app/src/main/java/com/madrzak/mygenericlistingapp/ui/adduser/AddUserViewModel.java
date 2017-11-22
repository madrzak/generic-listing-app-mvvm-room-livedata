package com.madrzak.mygenericlistingapp.ui.adduser;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;
import com.madrzak.mygenericlistingapp.data.model.UserModel;
import com.madrzak.mygenericlistingapp.data.source.UsersRepository;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Łukasz on 04/11/2017.
 */

public class AddUserViewModel extends AndroidViewModel {

    private UsersRepository usersRepository;

    private MutableLiveData<Boolean> userCreated = new MutableLiveData<>();

    public AddUserViewModel(Application application) {
        super(application);

        if (usersRepository == null) {
            usersRepository = UsersRepository.getInstance(AppDatabaseHelper.getInstance(application).getDatabase().userDao());
        }
    }

    public LiveData<Boolean> addUser(String name, String surname) {
        Timber.i("onSave %s %s ", name, surname);

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setSurname(surname);

        if (!userModel.isValid()) {
            userCreated.setValue(false);
            return userCreated;
        }

        userModel.setDateCreated(new Date());
        usersRepository.add(userModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    userCreated.setValue(aBoolean);

                }, throwable -> {
                    userCreated.setValue(false);
                    Timber.e(throwable);
                });
        return userCreated;
    }

}
