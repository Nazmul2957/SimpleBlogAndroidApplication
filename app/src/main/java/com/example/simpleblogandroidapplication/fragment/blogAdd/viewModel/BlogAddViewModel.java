package com.example.simpleblogandroidapplication.fragment.blogAdd.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simpleblogandroidapplication.activity.SplashActivity.BlogsInsertedStatus;
import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.database.db.LocalDatabase;
import com.example.simpleblogandroidapplication.fragment.blogAdd.repository.BlogAddRepository;
import com.example.simpleblogandroidapplication.model.Blog;

public class BlogAddViewModel extends AndroidViewModel implements BlogAddViewModel_View{
    private BlogAddRepository repository;
    private MutableLiveData<BlogsInsertedStatus> blogsInsertedStatusMutableLiveData=new MutableLiveData<>();
    private BlogsInsertedStatus blogsInsertedStatus;

    private LiveData<Boolean> isBlogInserted;;
    BlogDao blogDao;


    public BlogAddViewModel(@NonNull Application application) {
        super(application);
        LocalDatabase database=LocalDatabase.getReferences(application);
        blogDao=database.blogDao();
        blogsInsertedStatus =new BlogsInsertedStatus();
        blogsInsertedStatus.setBlogStore(false);
        repository=new BlogAddRepository(application,blogDao);
        repository.viewModel=this;

    }

    //observer function of isblog inserted to database
    public MutableLiveData<BlogsInsertedStatus>getBlogInsertedStatus(){
        return blogsInsertedStatusMutableLiveData;
    }

public void addBlog(Blog blog){
        repository.insertBlog(blog);
}


    @Override
    public void blogStatus(boolean blogStatus) {
        blogsInsertedStatus.setBlogStore(blogStatus);
        blogsInsertedStatusMutableLiveData.postValue(blogsInsertedStatus);
    }
}
