package com.example.thiqah.testretrofit.ItemView;


import com.example.thiqah.testretrofit.data.User;

interface ItemContract {

    interface View {
        void ShowItem(User user);
    }

    interface Presenter {
        void validateUser();
    }

}
