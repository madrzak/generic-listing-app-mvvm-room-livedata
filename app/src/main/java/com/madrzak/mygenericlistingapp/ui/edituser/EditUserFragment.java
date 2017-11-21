package com.madrzak.mygenericlistingapp.ui.edituser;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.madrzak.mygenericlistingapp.R;
import com.madrzak.mygenericlistingapp.data.model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Łukasz on 20/11/2017.
 */

public class EditUserFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_surname)
    EditText etSurname;

    private EditUserViewModel mEditUserViewModel;
    private int userId;
    private UserModel mUserModel;

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

            mEditUserViewModel.getUser(userId).observe(this, userModel -> {
                mUserModel = userModel;
                etName.setText(userModel.getName());
                etSurname.setText(userModel.getSurname());
            });
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
    }

    @OnClick(R.id.btn_save)
    public void onSave() {
        mUserModel.setName(etName.getText().toString());
        mUserModel.setSurname(etSurname.getText().toString());

        mEditUserViewModel.updateUser(mUserModel).observe(this, success -> {
            if (success) {
                Toast.makeText(getActivity(), "User edited", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "User not edited", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
