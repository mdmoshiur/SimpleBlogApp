package com.moshiur.simpleblogapp.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.moshiur.simpleblogapp.models.Blog;

@Database(entities = {Blog.class}, version = 1)
public abstract class BlogDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "BlogDatabase";

    private static BlogDatabase instance;

    public abstract BlogDao blogDao();

    //creates only one instance
    public static synchronized BlogDatabase getInstance(Context context){
        //no instance
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BlogDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}