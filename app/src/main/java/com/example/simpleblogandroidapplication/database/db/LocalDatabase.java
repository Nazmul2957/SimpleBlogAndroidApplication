package com.example.simpleblogandroidapplication.database.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.model.Blog;


@Database(entities = {Blog.class},version =4,exportSchema = false)

public abstract class LocalDatabase extends RoomDatabase {

    private static  LocalDatabase databaseRef;
    public abstract BlogDao blogDao();




    public static LocalDatabase getReferences(@NonNull Context context){
        if (databaseRef==null){
            synchronized (LocalDatabase.class){
                if (databaseRef == null ){
                    databaseRef= Room.databaseBuilder(context, LocalDatabase.class,"BlogDatabase").fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseRef;
    }
}
