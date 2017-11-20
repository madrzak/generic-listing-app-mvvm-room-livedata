package com.madrzak.mygenericlistingapp.ui.edituser;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.madrzak.mygenericlistingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Łukasz on 20/11/2017.
 */

public class EditUserFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";

    private int userId;

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_surname)
    EditText etSurname;


    private EditUserViewModel mEditUserViewModel;

    public static EditUserFragment newInstance(int userId) {
        EditUserFragment editUserFragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USER_ID, userId);
        editUserFragment.setArguments(args);

        return editUserFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEditUserViewModel = ViewModelProviders.of(this).get(EditUserViewModel.class);

        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_USER_ID);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.adduser_frag, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Timber.i("edit user %s", userId);


    }
}
