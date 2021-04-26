package com.example.simpleblogandroidapplication.activity.SplashActivity.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simpleblogandroidapplication.activity.SplashActivity.BlogsInsertedStatus;
import com.example.simpleblogandroidapplication.activity.SplashActivity.repository.SplashActivityRepository;
import com.example.simpleblogandroidapplication.database.dao.BlogDao;
import com.example.simpleblogandroidapplication.database.db.LocalDatabase;
import com.example.simpleblogandroidapplication.util.InternetConnectivityInfo;

public class SplashActivityViewModel extends AndroidViewModel implements SplashViewModel_View {
    private SplashActivityRepository repository;
    private InternetConnectivityInfo internetConnectivityInfo;
    private MutableLiveData<Boolean> isInternetOn=new MutableLiveData<>();
    private MutableLiveData<BlogsInsertedStatus> blogsInsertedStatusMutableLiveData=new MutableLiveData<>();
    BlogDao blogDao;
    private BlogsInsertedStatus blogsInsertedStatus;

    public SplashActivityViewModel(@NonNull Application application) {
        super(application);
        LocalDatabase database=LocalDatabase.getReferences(application);
        blogDao=database.blogDao();
        blogsInsertedStatus =new BlogsInsertedStatus();
        blogsInsertedStatus.setBlogStore(false);
        repository=new SplashActivityRepository(application);
        repository.viewModel=this;
        repository.getBlogs(blogDao);
    }

//observer function of isblog inserted to database
public MutableLiveData<BlogsInsertedStatus>getBlogsInsertedStatus(){
        return blogsInsertedStatusMutableLiveData;
}

    //observe internet Connection
    public LiveData<Boolean> getInternetConnectivityInfo(){
        return isInternetOn;
    }

    @Override
    public void blogStatus(boolean blogStatus) {

        blogsInsertedStatus.setBlogStore(blogStatus);
        blogsInsertedStatusMutableLiveData.postValue(blogsInsertedStatus);
    }
    //this Method check Internet Connection
    public void checkInternetConnectivity(Context application) {
        if (internetConnectivityInfo==null){
            internetConnectivityInfo= new InternetConnectivityInfo(application);
        }
        if (internetConnectivityInfo.isConnected()){
            isInternetOn.postValue(true);
        }else {
            isInternetOn.postValue(false);
        }
    }
}
