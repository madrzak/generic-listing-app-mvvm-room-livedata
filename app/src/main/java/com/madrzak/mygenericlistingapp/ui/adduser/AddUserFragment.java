package com.madrzak.mygenericlistingapp.ui.adduser;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Łukasz on 04/11/2017.
 */

public class AddUserFragment extends Fragment {

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_surname)
    EditText etSurname;

    private AddUserViewModel mViewModel;

    public AddUserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mViewModel = ViewModelProviders.of(this).get(AddUserViewModel.class);

        mViewModel.getUserCreated().observe(this, aBoolean -> {
            if (aBoolean) {
                clearForm();
                Toast.makeText(getActivity(), "User created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "User not created", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.adduser_frag, container, false);

        ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    @OnClick(R.id.btn_save)
    public void onSave() {

        mViewModel.addUser(etName.getText().toString(), etSurname.getText().toString());

    }

    private void clearForm(){
        etName.setText("");
        etSurname.setText("");
    }
}
