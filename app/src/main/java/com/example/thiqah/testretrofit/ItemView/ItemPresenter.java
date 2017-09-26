package com.example.thiqah.testretrofit.ItemView;

import android.content.Context;

import com.example.thiqah.testretrofit.data.User;
import com.example.thiqah.testretrofit.data.UsersDataSource;
import com.example.thiqah.testretrofit.data.source.UserRepository;
import com.example.thiqah.testretrofit.data.source.local.UsersLocalDataSource;
import com.example.thiqah.testretrofit.data.source.remote.UsersRemoteDataSource;

import java.util.ArrayList;

class ItemPresenter implements ItemContract.Presenter {

    private ItemContract.View view;
    private String userId;
    private UserRepository userRepository;
    private UsersRemoteDataSource usersRemoteDataSource;
    private UsersLocalDataSource usersLocalDataSource;


    ItemPresenter(Context context, String userId, ItemContract.View view) {
        this.view = view;
        this.userId = userId;
        usersRemoteDataSource = UsersRemoteDataSource.newInstance();
        usersLocalDataSource = UsersLocalDataSource.newInstance(context);
        userRepository = UserRepository.newInstance(usersRemoteDataSource, usersLocalDataSource);
    }

    @Override
    public void validateUser() {
        userRepository.getUser(userId, new UsersDataSource.GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                view.ShowItem(user);
            }

            @Override
            public void OnDataNotAvailable() {

            }
        });
    }
}
