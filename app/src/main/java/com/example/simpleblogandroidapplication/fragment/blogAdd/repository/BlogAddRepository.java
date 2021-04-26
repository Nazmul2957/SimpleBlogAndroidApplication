package com.example.simpleblogandroidapplication.fragment.blogAdd.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.fragment.blogAdd.viewModel.BlogAddViewModel;
import com.example.simpleblogandroidapplication.model.Blog;

import java.util.concurrent.ExecutionException;

public class BlogAddRepository {

    public BlogAddViewModel viewModel;
    BlogDao blogDao;
    Context context;

    public BlogAddRepository(Context context,BlogDao blogDao){
        this.context=context;
        this.blogDao=blogDao;
    }


    public void insertBlog(Blog blog){

        try {
            Long result= new InsertBlog(blogDao).execute(blog).get();
            if(result>0){
                viewModel.blogStatus(true);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
            viewModel.blogStatus(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
            viewModel.blogStatus(false);
        }

    }
    //===================For Background Work================

    private class InsertBlog extends AsyncTask<Blog, Void, Long> {

        private BlogDao blogDao;

        public InsertBlog(BlogDao blogDao){
            this.blogDao=blogDao;
        }

        @Override
        protected Long doInBackground(Blog... blog) {

            return blogDao.insertSingel(blog[0]);
        }
    }

}
