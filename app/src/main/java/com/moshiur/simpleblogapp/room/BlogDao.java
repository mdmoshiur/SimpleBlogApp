package com.moshiur.simpleblogapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.moshiur.simpleblogapp.models.Blog;

import java.util.List;

@Dao
public interface BlogDao {

    @Insert
    void createBlog(Blog blog);

    @Update
    void editBlog(Blog blog);

    @Query("SELECT * FROM BlogTable")
    LiveData<List<Blog>> getAllBLogs();
}
