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
import lombok.Getter;
import timber.log.Timber;

/**
 * Created by Łukasz on 04/11/2017.
 */

public class AddUserViewModel extends AndroidViewModel {

    private UsersRepository usersRepository;

    @Getter
    private MutableLiveData<Boolean> userCreated;

    public AddUserViewModel(Application application) {
        super(application);


        if (usersRepository == null) {
            usersRepository = new UsersRepository(AppDatabaseHelper.getInstance(application).getDatabase().userDao());
        }
    }

    public void addUser(String name, String surname) {
        Timber.i("onSave %s %s ", name, surname);

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setSurname(surname);

        if (!userModel.isValid()) {
            userCreated.setValue(false);
            return;
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
    }

    public LiveData<Boolean> getUserCreated() {
        if (userCreated == null) {
            userCreated = new MutableLiveData<>();
        }
        return userCreated;
    }
}
