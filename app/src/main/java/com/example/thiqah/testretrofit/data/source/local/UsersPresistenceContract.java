package com.example.thiqah.testretrofit.data.source.local;

import android.provider.BaseColumns;

public class UsersPresistenceContract {

    private UsersPresistenceContract() {
    }


    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN__ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_USER_ID = "userId";
        public static final String COLUMN_NAME_COMPLETED = "completed";
    }
}
