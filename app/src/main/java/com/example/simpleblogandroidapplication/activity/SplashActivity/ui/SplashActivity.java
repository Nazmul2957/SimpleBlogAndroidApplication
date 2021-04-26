package com.example.simpleblogandroidapplication.activity.SplashActivity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.simpleblogandroidapplication.activity.MainActivity;
import com.example.simpleblogandroidapplication.activity.SplashActivity.BlogsInsertedStatus;
import com.example.simpleblogandroidapplication.activity.SplashActivity.viewModel.SplashActivityViewModel;
import com.example.simpleblogandroidapplication.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
     ActivitySplashBinding binding;
    private SplashActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider(this).get(SplashActivityViewModel.class);
        viewModel.checkInternetConnectivity(this);

        binding.progress.startProgress();


        observer();
    }
    private void gotoHomeScreen() {
        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void observer() {
        //observe Network Connectivity
        viewModel.getInternetConnectivityInfo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean connected) {
                if (!connected){
                    Toast.makeText(SplashActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                    gotoHomeScreen();
                }
            }
        });
        viewModel.getBlogsInsertedStatus().observe(this, new Observer<BlogsInsertedStatus>() {
            @Override
            public void onChanged(BlogsInsertedStatus blogsInsertedStatus) {
                if(blogsInsertedStatus.isBlogStore()){
                    Log.e("tesst","hahaha");
                    gotoHomeScreen();
                }
            }
        });
    }
}