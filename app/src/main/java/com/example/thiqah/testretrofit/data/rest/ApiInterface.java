package com.example.thiqah.testretrofit.data.rest;

import com.example.thiqah.testretrofit.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("todos")
    Call<List<User>> getTodos();
}
