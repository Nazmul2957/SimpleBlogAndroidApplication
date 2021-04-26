package com.example.simpleblogandroidapplication.network;

import com.example.simpleblogandroidapplication.model.BlogContainer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/sohel-cse/simple-blog-api/db")
    Call<BlogContainer> getBlogs();
}
