package com.example.simpleblogandroidapplication.fragment.blogEdit.viewModel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.database.db.LocalDatabase;
import com.example.simpleblogandroidapplication.fragment.blogAdd.viewModel.BlogAddViewModel_View;
import com.example.simpleblogandroidapplication.fragment.blogEdit.BlogsUpdateStatus;
import com.example.simpleblogandroidapplication.fragment.blogEdit.repository.BlogEditRepository;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.util.Constants;

public class BlogEditViewModel extends AndroidViewModel implements BlogAddViewModel_View {
    private BlogEditRepository repository;
    private MutableLiveData<BlogsUpdateStatus> blogsUpdateStatusMutableLiveData = new MutableLiveData<>();
    private BlogsUpdateStatus blogsUpdateStatus;
    private MutableLiveData<String> status = new MutableLiveData<>();
    LiveData<Blog> blogLiveData = new MutableLiveData<>();

    BlogDao blogDao;


    public BlogEditViewModel(@NonNull Application application) {
        super(application);
        LocalDatabase database = LocalDatabase.getReferences(application);
        blogDao = database.blogDao();
        blogsUpdateStatus = new BlogsUpdateStatus();
        blogsUpdateStatus.setBlogUpdate(false);
        repository = new BlogEditRepository(application, blogDao);
        repository.viewModel = this;

    }

    public void BlogById(Bundle bundle) {

        if (bundle != null) {
            String blogId = bundle.getString(Constants.BLOGID, null);

            if (blogId != null) {
                this.blogLiveData = repository.getBlogById(blogId);
            } else {
                status.postValue("No Blog Found");
            }
        } else {
            status.postValue("Error");
        }
    }
    public LiveData<Blog> Blog(){
        return blogLiveData;
    }
    //observer function of isblog inserted to database
    public MutableLiveData<BlogsUpdateStatus> getBlogUpdateStatus() {
        return blogsUpdateStatusMutableLiveData;
    }

    public MutableLiveData<String> getStatus() {
        return status;
    }

    public void updateBlog(Blog blog) {
        repository.updateBlog(blog);
    }


    @Override
    public void blogStatus(boolean blogStatus) {
        blogsUpdateStatus.setBlogUpdate(blogStatus);
        blogsUpdateStatusMutableLiveData.postValue(blogsUpdateStatus);
    }
}
