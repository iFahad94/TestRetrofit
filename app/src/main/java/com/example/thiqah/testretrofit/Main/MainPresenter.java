package com.example.thiqah.testretrofit.Main;

import android.content.Context;

import com.example.thiqah.testretrofit.data.User;
import com.example.thiqah.testretrofit.data.source.local.UsersLocalDataSource;
import com.example.thiqah.testretrofit.data.source.UserRepository;
import com.example.thiqah.testretrofit.data.UsersDataSource;
import com.example.thiqah.testretrofit.data.source.remote.UsersRemoteDataSource;

import java.util.ArrayList;

class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private UserRepository userRepository;
    private UsersRemoteDataSource usersRemoteDataSource;
    private UsersLocalDataSource usersLocalDataSource;
    private Context context;

    MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
        usersRemoteDataSource = UsersRemoteDataSource.newInstance();
        usersLocalDataSource = UsersLocalDataSource.newInstance(context);
        userRepository = UserRepository.newInstance(usersRemoteDataSource, usersLocalDataSource);
    }

    void loadUsers() {
        userRepository.getUser(new UsersDataSource.LoadUserCallBack() {
            @Override
            public void onUserLoaded(ArrayList<User> users) {
                view.showUsers(users);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void openUserDetails(User user) {
        view.openUserView(user.getTitle());
    }
}
