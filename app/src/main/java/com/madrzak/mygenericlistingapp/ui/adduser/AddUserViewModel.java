package com.madrzak.mygenericlistingapp.ui.adduser;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;
import com.madrzak.mygenericlistingapp.data.model.UserModel;
import com.madrzak.mygenericlistingapp.data.source.UsersRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Łukasz on 04/11/2017.
 */

public class AddUserViewModel extends AndroidViewModel {

    private UsersRepository usersRepository;

    private LiveData<List<UserModel>> users;
    private LiveData<Boolean> userCreated = new LiveData<Boolean>() {};


    public AddUserViewModel(Application application) {
        super(application);


        if (usersRepository == null) {
            usersRepository = new UsersRepository(AppDatabaseHelper.getInstance().getDatabase().userDao());
        }
    }

    public LiveData<List<UserModel>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }

        return users;
    }

    private void loadUsers() {
        Timber.i("loadUsers");

        users = usersRepository.getAll();
    }


    public void addUser(String name, String surname) {
        Timber.i("onSave %s %s ", name, surname);

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setSurname(surname);

        // TODO validate object

        // TODO notify view on success or failure

        usersRepository.add(userModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> {
//            userCreated.

        });
    }
}
