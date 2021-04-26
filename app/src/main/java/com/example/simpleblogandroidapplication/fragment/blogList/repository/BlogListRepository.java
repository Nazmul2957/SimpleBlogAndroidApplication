package com.example.simpleblogandroidapplication.fragment.blogList.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.fragment.blogList.viewModel.BlogListViewModel;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.network.Api;
import com.example.simpleblogandroidapplication.network.RetrofitClient;

import java.util.List;

public class BlogListRepository {
    private Api api;
    public BlogListViewModel viewModel;
    Context context;

    public BlogListRepository(Context context){
        this.context=context;
        api= RetrofitClient.get().create(Api.class);
    }

    public LiveData<List<Blog>> getBlogs(BlogDao blogDao){
        return blogDao.getBlogs();
    }



}
