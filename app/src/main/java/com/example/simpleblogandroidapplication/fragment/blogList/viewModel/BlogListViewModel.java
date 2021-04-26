package com.example.simpleblogandroidapplication.fragment.blogList.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.database.db.LocalDatabase;
import com.example.simpleblogandroidapplication.fragment.blogList.repository.BlogListRepository;
import com.example.simpleblogandroidapplication.model.Blog;

import java.util.List;

public class BlogListViewModel extends AndroidViewModel {
    private BlogListRepository repository;


    private LiveData<List<Blog>> BlogsLivedata;;
    BlogDao blogDao;


    public BlogListViewModel(@NonNull Application application) {
        super(application);
        LocalDatabase database=LocalDatabase.getReferences(application);
        blogDao=database.blogDao();

        repository=new BlogListRepository(application);
        repository.viewModel=this;
        BlogsLivedata=repository.getBlogs(blogDao);
    }

//observer function of isblog inserted to database
public LiveData<List<Blog>>getBlogsLiveData(){
        return BlogsLivedata;
}




}
