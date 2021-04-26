package com.example.simpleblogandroidapplication.fragment.blogDetails.viewModel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.database.db.LocalDatabase;
import com.example.simpleblogandroidapplication.fragment.blogDetails.repository.BlogDetailsRepository;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.util.Constants;

public class BlogDetailsViewModel extends AndroidViewModel  {
    private BlogDetailsRepository repository;
    BlogDao blogDao;
    private MutableLiveData<String> status = new MutableLiveData<>();
    LiveData<Blog> blogLiveData=new MutableLiveData<>();

    public BlogDetailsViewModel(@NonNull Application application) {
        super(application);
        LocalDatabase database=LocalDatabase.getReferences(application);
        blogDao=database.blogDao();

        repository=new BlogDetailsRepository();

    }

    public MutableLiveData<String> getStatus() {
        return status;
    }
    public LiveData<Blog> Blog(){
        return blogLiveData;
    }

public void BlogDetails(Bundle bundle){

    if (bundle != null) {
        String blogId = bundle.getString(Constants.BLOGID, null);

        if (blogId != null) {
            this.blogLiveData = repository.getBlogDetails(blogDao, blogId);
        } else {
            status.postValue("No Blog Found");
        }
    }

     else {
        status.postValue("Error");
    }
}


}
