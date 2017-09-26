package com.example.thiqah.testretrofit.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thiqah.testretrofit.data.User;
import com.example.thiqah.testretrofit.data.UsersDataSource;

import java.util.ArrayList;

public class UsersLocalDataSource implements UsersDataSource {

    private static UsersLocalDataSource INSTANCE;

    private UsersDbHelper dbHelper;

    private UsersLocalDataSource(Context context) {
        dbHelper = new UsersDbHelper(context);
    }

    public static UsersLocalDataSource newInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new UsersLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getUser(LoadUserCallBack callBack) {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                UsersPresistenceContract.UserEntry.COLUMN__ID,
                UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE,
                UsersPresistenceContract.UserEntry.COLUMN_USER_ID
        };

        Cursor c = db.query(
                UsersPresistenceContract.UserEntry.TABLE_NAME, projection, null, null, null, null, null);

        User user = null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isLast()) {
                int itemId = c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN__ID));
                String title = c.getString(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE));
                int description =
                        c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_USER_ID));
                user = new User(itemId, description, title);
                users.add(user);
                c.moveToNext();
            }
            int itemId = c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN__ID));
            String title = c.getString(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE));
            int description =
                    c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_USER_ID));
            user = new User(itemId, description, title);
            users.add(user);
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (!users.isEmpty()) {
            callBack.onUserLoaded(users);
        } else {
            callBack.onDataNotAvailable();
        }
    }

    @Override
    public void getUser(String userId, GetUserCallback callback) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                UsersPresistenceContract.UserEntry.COLUMN__ID,
                UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE,
                UsersPresistenceContract.UserEntry.COLUMN_USER_ID
        };

        Cursor c = db.query(
                UsersPresistenceContract.UserEntry.TABLE_NAME, projection, null, null, null, null, null);

        User user = null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isLast()) {
                int itemId = c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN__ID));
                String title = c.getString(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE));
                int description =
                        c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_USER_ID));

                if (title.equals(userId)) {
                    user = new User(itemId, description, title);
                    break;
                }
                c.moveToNext();
            }
            int itemId = c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN__ID));
            String title = c.getString(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE));
            int description =
                    c.getInt(c.getColumnIndexOrThrow(UsersPresistenceContract.UserEntry.COLUMN_USER_ID));
            if (title.equals(userId)) {
                user = new User(itemId, description, title);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        if (user != null) {
            callback.onUserLoaded(user);
        } else {
            callback.OnDataNotAvailable();
        }
    }


    @Override
    public void saveUser(ArrayList<User> users) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (User user : users) {
            ContentValues values = new ContentValues();
            values.put(UsersPresistenceContract.UserEntry.COLUMN__ID, user.getId());
            values.put(UsersPresistenceContract.UserEntry.COLUMN_NAME_TITLE, user.getTitle());
            values.put(UsersPresistenceContract.UserEntry.COLUMN_USER_ID, user.getUserId());
            db.insert(UsersPresistenceContract.UserEntry.TABLE_NAME, null, values);
        }
        db.close();
    }
}