package com.example.simpleblogandroidapplication.activity.SplashActivity.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.simpleblogandroidapplication.activity.SplashActivity.viewModel.SplashViewModel_View;
import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.model.BlogContainer;
import com.example.simpleblogandroidapplication.network.Api;
import com.example.simpleblogandroidapplication.network.RetrofitClient;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivityRepository {
    private Api api;
    public SplashViewModel_View viewModel;
    Context context;

    public SplashActivityRepository(Context context){
        this.context=context;
        api= RetrofitClient.get().create(Api.class);
    }

    public void getBlogs(BlogDao blogDao){
         api.getBlogs().enqueue(new Callback<BlogContainer>() {
             @Override
             public void onResponse(Call<BlogContainer> call, Response<BlogContainer> response) {
                 Log.e("tesst","getProduct  "+"start");
                 if (response.isSuccessful() && response.body() != null){
                     Log.e("tesst","getProduct  "+response.body().getBlogs().toString()+"finish");
                     try {
                         Boolean isInserted= new InsertBlogList(blogDao).execute(response.body().getBlogs()).get();
                         viewModel.blogStatus(isInserted);
                     } catch (ExecutionException e) {
                         viewModel.blogStatus(false);
                     } catch (InterruptedException e) {
                         viewModel.blogStatus(false);
                     }


                 }
                 else {
                     viewModel.blogStatus(false);
                     Log.e("tesst","getProduct  "+"Failed");
                 }
             }

             @Override
             public void onFailure(Call<BlogContainer> call, Throwable t) {
                 viewModel.blogStatus(false);
             }
         });
    }


    //===================For Background Work================

    private class InsertBlogList extends AsyncTask<List<Blog>, Void, Boolean> {

        private BlogDao blogDao;

        public InsertBlogList(BlogDao blogDao){
            this.blogDao=blogDao;
        }

        @Override
        protected Boolean doInBackground(List<Blog>... lists) {
           // blogDao.clear();
            blogDao.insertList(lists[0]);
            return true;
        }
    }
}
