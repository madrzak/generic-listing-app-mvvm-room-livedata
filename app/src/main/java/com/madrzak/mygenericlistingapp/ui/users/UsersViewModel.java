package com.madrzak.mygenericlistingapp.ui.users;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;
import com.madrzak.mygenericlistingapp.data.model.UserModel;
import com.madrzak.mygenericlistingapp.data.source.UsersRepository;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Łukasz on 18/11/2017.
 */

public class UsersViewModel extends AndroidViewModel {

    private UsersRepository usersRepository;

    private LiveData<List<UserModel>> users;

    public UsersViewModel(Application application) {
        super(application);

        if (usersRepository == null) {
            usersRepository = new UsersRepository(AppDatabaseHelper.getInstance(application).getDatabase().userDao());
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

}