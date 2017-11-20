package com.madrzak.mygenericlistingapp.ui.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madrzak.mygenericlistingapp.R;
import com.madrzak.mygenericlistingapp.data.model.UserModel;
import com.madrzak.mygenericlistingapp.util.DatesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Łukasz on 18/11/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private final UsersViewModel mUsersViewModel;
    private final UsersFragment.OnUserSelectedListener mListener;

    private List<UserModel> mUsers = new ArrayList<>();

    public UsersAdapter(UsersViewModel usersViewModel, UsersFragment.OnUserSelectedListener listener) {
        this.mUsersViewModel = usersViewModel;
        this.mListener = listener;
    }

    public void replaceData(List<UserModel> users) {
        setList(users);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_surname)
        TextView tvSurname;

        @BindView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.users_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel userModel = mUsers.get(position);

        holder.tvName.setText(userModel.getName());
        holder.tvSurname.setText(userModel.getSurname());
        holder.tvDate.setText(DatesUtil.format(userModel.getDateCreated()));

        holder.itemView.setOnClickListener(view -> {
            mListener.onUserSelected(userModel.getUid());
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    private void setList(List<UserModel> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

}
