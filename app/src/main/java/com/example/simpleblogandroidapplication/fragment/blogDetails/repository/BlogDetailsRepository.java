package com.example.simpleblogandroidapplication.fragment.blogDetails.repository;

import androidx.lifecycle.LiveData;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.fragment.blogDetails.viewModel.BlogDetailsViewModel;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.network.Api;
import com.example.simpleblogandroidapplication.network.RetrofitClient;

public class BlogDetailsRepository {
    private Api api;
    public BlogDetailsViewModel viewModel;

    public BlogDetailsRepository(){

        api= RetrofitClient.get().create(Api.class);
    }
    public LiveData<Blog> getBlogDetails(BlogDao blogDao,String blogId){
        return blogDao.getBlogById(blogId);
    }


}
