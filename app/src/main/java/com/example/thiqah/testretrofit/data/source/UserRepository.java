package com.example.thiqah.testretrofit.data.source;

import com.example.thiqah.testretrofit.data.User;
import com.example.thiqah.testretrofit.data.UsersDataSource;

import java.util.ArrayList;

import static android.R.attr.x;

public class UserRepository implements UsersDataSource {

    private static UserRepository INSTANCE;

    private final UsersDataSource mTasksRemoteDataSource;
    private final UsersDataSource mTasksLocalDataSource;

    public UserRepository(UsersDataSource mTasksLocalDataSource, UsersDataSource mTasksRemoteDataSource) {
        this.mTasksLocalDataSource = mTasksLocalDataSource;
        this.mTasksRemoteDataSource = mTasksRemoteDataSource;
    }

    public static UserRepository newInstance(UsersDataSource usersRemoteDataSource,
                                             UsersDataSource usersLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(usersLocalDataSource, usersRemoteDataSource);
        }
        return INSTANCE;
    }


    @Override
    public void getUser(String userId, final GetUserCallback callback) {
        mTasksLocalDataSource.getUser(userId, new GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                callback.onUserLoaded(user);
            }

            @Override
            public void OnDataNotAvailable() {

            }
        });
    }

    @Override
    public void getUser(final LoadUserCallBack callBack) {
        mTasksLocalDataSource.getUser(new LoadUserCallBack() {

            @Override
            public void onUserLoaded(ArrayList<User> users) {
                callBack.onUserLoaded(users);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksRemoteDataSource.getUser(new LoadUserCallBack() {
                    @Override
                    public void onUserLoaded(ArrayList<User> users) {
                        callBack.onUserLoaded(users);
                        mTasksLocalDataSource.saveUser(users);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callBack.onDataNotAvailable();
                    }
                });
            }
        });
    }

    @Override
    public void saveUser(ArrayList<User> users) {

    }
}