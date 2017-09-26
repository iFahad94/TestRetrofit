package com.example.thiqah.testretrofit.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Tasks.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UsersPresistenceContract.UserEntry.TABLE_NAME + " (" +
                    UsersPresistenceContract.UserEntry.COLUMN__ID + INTEGER + " PRIMARY KEY," +
                    UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    UsersPresistenceContract.UserEntry.COLUMN_USER_ID + INTEGER +
                    " )";

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
