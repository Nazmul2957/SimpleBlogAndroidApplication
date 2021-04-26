package com.example.simpleblogandroidapplication.fragment.blogEdit.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.fragment.blogEdit.viewModel.BlogEditViewModel;
import com.example.simpleblogandroidapplication.model.Blog;

import java.util.concurrent.ExecutionException;

public class BlogEditRepository {
    public BlogEditViewModel viewModel;
    BlogDao blogDao;
    Context context;

    public BlogEditRepository(Context context, BlogDao blogDao) {
        this.context = context;
        this.blogDao = blogDao;
    }

    public LiveData<Blog> getBlogById(String id) {
        return blogDao.getBlogById(id);
    }

    public void updateBlog(Blog blog) {

        try {
            int result = new UpdateBlog(blogDao).execute(blog).get();
            if (result > 0) {
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

    private class UpdateBlog extends AsyncTask<Blog, Void, Integer> {

        private BlogDao blogDao;

        public UpdateBlog(BlogDao blogDao) {
            this.blogDao = blogDao;
        }

        @Override
        protected Integer doInBackground(Blog... blog) {

            return blogDao.update(blog[0]);
        }
    }

}
