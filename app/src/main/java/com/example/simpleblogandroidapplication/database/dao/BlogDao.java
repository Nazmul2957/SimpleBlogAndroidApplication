package com.example.simpleblogandroidapplication.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.simpleblogandroidapplication.model.Blog;

import java.util.List;
@Dao
public interface BlogDao {
    //insert multiple blog
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Blog> blog);

    //insert sigle blog
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSingel(Blog Blog);

    @Update
    int update(Blog blog);

    @Query("DELETE FROM Blog")
    void clear();


    @Query("SELECT * FROM Blog")
    LiveData<List<Blog>> getBlogs();

    @Query("SELECT * FROM Blog WHERE id LIKE :id")
    LiveData<Blog> getBlogById(String id);
}
