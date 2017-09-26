package com.example.thiqah.testretrofit.Main;

import com.example.thiqah.testretrofit.data.User;

import java.util.ArrayList;

interface MainContract {

    interface View {
        void showUsers(ArrayList<User> users);

        void openUserView(String title);
    }

    interface Presenter {
        void openUserDetails(User user);
    }
}
