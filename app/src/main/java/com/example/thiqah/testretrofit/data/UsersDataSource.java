package com.example.thiqah.testretrofit.data;

import java.util.ArrayList;

public interface UsersDataSource {


    interface LoadUserCallBack {

        void onUserLoaded(ArrayList<User> users);

        void onDataNotAvailable();
    }

    void getUser(LoadUserCallBack callBack);


    interface GetUserCallback {
        void onUserLoaded(User user);

        void OnDataNotAvailable();
    }

    void getUser(String userId, GetUserCallback callback);

    void saveUser(ArrayList<User> users);
}
