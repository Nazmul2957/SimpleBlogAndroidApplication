package com.example.simpleblogandroidapplication.fragment.blogAdd.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.simpleblogandroidapplication.activity.SplashActivity.BlogsInsertedStatus;
import com.example.simpleblogandroidapplication.fragment.blogAdd.viewModel.BlogAddViewModel;

import com.example.simpleblogandroidapplication.model.Author;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.R;
import com.example.simpleblogandroidapplication.databinding.FragmentBlogAddBinding;

import java.util.ArrayList;
import java.util.List;


public class BlogAdd extends Fragment {

    private BlogAddViewModel viewModel;
    private FragmentBlogAddBinding binding;
    public BlogAdd() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlogAddBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BlogAddViewModel.class);
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataValidation();
            }
        });
        Observer();
    }

    private void Observer() {
        viewModel.getBlogInsertedStatus().observe(getViewLifecycleOwner(), new Observer<BlogsInsertedStatus>() {
            @Override
            public void onChanged(BlogsInsertedStatus blogsInsertedStatus) {
                if(blogsInsertedStatus.isBlogStore()) {
                    clear();
                    Toast.makeText(getContext(), "Successfully Added A Blog", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void dataValidation() {
        if(!TextUtils.isEmpty(binding.titleEdt.getText().toString())){
            if (!TextUtils.isEmpty(binding.desEdt.getText().toString())){
                if(getCheckedCategories().size()>0){
                    Author author=new Author(Integer.valueOf(getResources().getString(R.string.author_id))
                            ,getResources().getString(R.string.author_name)
                            ,getResources().getString(R.string.author_avatar
                    )       ,getResources().getString(R.string.author_profession));

                    viewModel.addBlog(new Blog(binding.titleEdt.getText().toString()
                            , binding.desEdt.getText().toString()
                            , getCheckedCategories()
                            ,author));
                }
                else {
                    Toast.makeText(getContext(), "Please Select a Category", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                binding.desEdt.setError("Please Enter Description");
                binding.desEdt.requestFocus();
            }
        }
        else {
            binding.titleEdt.setError("Please Enter Title");
            binding.titleEdt.requestFocus();
        }
    }
    private List<String> getCheckedCategories(){
        List<String> categories=new ArrayList<>();
        if(binding.businessBx.isChecked()){
            categories.add(getResources().getString(R.string.business_chk));
        }
        if(binding.lifestyleBx.isChecked()){
            categories.add(getResources().getString(R.string.lifestyle_chk));
        }
        if(binding.entertainmentChkBx.isChecked()){
            categories.add(getResources().getString(R.string.entertainment_chk));
        }
        if(binding.productivityBx.isChecked()){
            categories.add(getResources().getString(R.string.productivity_chk));
        }
        return categories;
    }
    public void clear(){
        binding.titleEdt.setText("");
        binding.desEdt.setText("");
        binding.businessBx.setChecked(false);
        binding.entertainmentChkBx.setChecked(false);
        binding.productivityBx.setChecked(false);
        binding.entertainmentChkBx.setChecked(false);
        binding.lifestyleBx.setChecked(false);
    }
}