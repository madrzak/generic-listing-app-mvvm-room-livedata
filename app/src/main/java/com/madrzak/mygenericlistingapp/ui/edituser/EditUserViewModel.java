package com.madrzak.mygenericlistingapp.ui.edituser;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;
import com.madrzak.mygenericlistingapp.data.source.UsersRepository;

/**
 * Created by Łukasz on 20/11/2017.
 */

public class EditUserViewModel extends AndroidViewModel {

    private UsersRepository mUsersRepository;


    public EditUserViewModel(Application application) {
        super(application);

        if (mUsersRepository == null) {
            mUsersRepository = new UsersRepository(AppDatabaseHelper.getInstance(application).getDatabase().userDao());
        }
    }
}
