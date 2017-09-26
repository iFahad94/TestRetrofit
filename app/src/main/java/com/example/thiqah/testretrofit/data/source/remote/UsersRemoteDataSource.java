package com.example.thiqah.testretrofit.data.source.remote;


import com.example.thiqah.testretrofit.data.User;
import com.example.thiqah.testretrofit.data.UsersDataSource;
import com.example.thiqah.testretrofit.data.rest.ApiClient;
import com.example.thiqah.testretrofit.data.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRemoteDataSource implements UsersDataSource {

    private static UsersRemoteDataSource INSTANCE;

    private UsersRemoteDataSource() {
    }

    public static UsersRemoteDataSource newInstance() {

        if (INSTANCE == null) {
            INSTANCE = new UsersRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getUser(final LoadUserCallBack callBack) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getTodos();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                callBack.onUserLoaded((ArrayList<User>) response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getUser(String userId, GetUserCallback callback) {

    }

    @Override
    public void saveUser(ArrayList<User> users) {

    }
}
