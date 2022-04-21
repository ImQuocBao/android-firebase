package com.example.firsebaseproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.firsebaseproject.UserDetail;

@Database(entities = {UserDetail.class}, version = 1)
public abstract class UserDetailDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "userDetail.db";
    private static UserDetailDataBase instance;

    public static synchronized UserDetailDataBase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDetailDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    };

    public abstract UserDetailDao userDetailDao();
}
