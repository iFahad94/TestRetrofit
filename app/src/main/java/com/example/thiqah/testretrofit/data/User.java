package com.example.thiqah.testretrofit.data;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("completed")
    private boolean completed;

    public User(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public User(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

}
