package com.madrzak.mygenericlistingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.madrzak.mygenericlistingapp.ui.adduser.AddUserFragment;
import com.madrzak.mygenericlistingapp.ui.edituser.EditUserFragment;
import com.madrzak.mygenericlistingapp.ui.users.UsersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Łukasz on 04/11/2017.
 */

public class MainActivity extends FragmentActivity implements UsersFragment.OnUserSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.adduser_act, null);
        ButterKnife.bind(this, view);
        setContentView(view);

        changeFragment(new AddUserFragment());

        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_add:
                    changeFragment(new AddUserFragment());
                    break;
                case R.id.action_list:
                    changeFragment(new UsersFragment());
                    break;
            }
            return true;

        });
    }


    private void changeFragment(final Fragment fragment) {

        if (isFragmentInBackstack(fragment.getClass().getName())) {
            getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getName(), 0);

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
        }
    }

    private boolean isFragmentInBackstack(final String fragmentTagName) {
        for (int entry = 0; entry < getSupportFragmentManager().getBackStackEntryCount(); entry++) {
            if (fragmentTagName.equals(getSupportFragmentManager().getBackStackEntryAt(entry).getName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onUserSelected(int userId) {
        Timber.i("Edit user %s", userId);
        EditUserFragment f = EditUserFragment.newInstance(userId);
        changeFragment(f);
    }

}
