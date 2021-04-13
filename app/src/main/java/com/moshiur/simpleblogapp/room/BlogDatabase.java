package com.moshiur.simpleblogapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.moshiur.simpleblogapp.models.Blog;

import static com.moshiur.simpleblogapp.utils.Constants.DATABASE_NAME;

@Database(entities = {Blog.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class BlogDatabase extends RoomDatabase {

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