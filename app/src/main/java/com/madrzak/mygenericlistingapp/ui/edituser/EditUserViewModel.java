package com.madrzak.mygenericlistingapp.ui.edituser;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;
import com.madrzak.mygenericlistingapp.data.model.UserModel;
import com.madrzak.mygenericlistingapp.data.source.UsersRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import timber.log.Timber;

/**
 * Created by Łukasz on 20/11/2017.
 */

public class EditUserViewModel extends AndroidViewModel {

    private UsersRepository mUsersRepository;

    @Getter
    private MutableLiveData<Boolean> userUpdated;

    public EditUserViewModel(Application application) {
        super(application);

        if (mUsersRepository == null) {
            mUsersRepository = new UsersRepository(AppDatabaseHelper.getInstance(application).getDatabase().userDao());
        }
    }

    public LiveData<UserModel> getUser(int userId) {
        return mUsersRepository.getUser(userId);
    }

    // TODO establish if this should return void instead and provide a separate method for getting LiveData object
    public LiveData<Boolean> updateUser(final UserModel userModel) {

        if (!userModel.isValid()) {
            userUpdated.setValue(false);
        }

        mUsersRepository.update(userModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                    if (success) {
                        userUpdated.setValue(true);
                    } else {
                        userUpdated.setValue(false);
                    }
                }, throwable -> {
                    userUpdated.setValue(false);
                    Timber.e(throwable);
                });

        return userUpdated;
    }

}
