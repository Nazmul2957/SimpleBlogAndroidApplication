package com.example.simpleblogandroidapplication.fragment.blogEdit.ui;

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

import com.example.simpleblogandroidapplication.fragment.blogEdit.BlogsUpdateStatus;
import com.example.simpleblogandroidapplication.fragment.blogEdit.viewModel.BlogEditViewModel;
import com.example.simpleblogandroidapplication.model.Author;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.R;
import com.example.simpleblogandroidapplication.databinding.FragmentBlogEditBinding;

import java.util.ArrayList;
import java.util.List;


public class BlogEdit extends Fragment {

    private BlogEditViewModel viewModel;
    private FragmentBlogEditBinding binding;
    Blog existingBlog;

    public BlogEdit() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlogEditBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BlogEditViewModel.class);
        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataValidation();
            }

        });
        viewModel.BlogById(getArguments());
        Observer();
    }

    private void Observer() {
        viewModel.Blog().observe(getViewLifecycleOwner(), new Observer<Blog>() {
            @Override
            public void onChanged(Blog blog) {
                existingBlog=blog;
                binding.titleEdt.setText(blog.getTitle());
                binding.desEdt.setText(blog.getDescription());
                for (int i = 0; i < blog.getCategories().size(); i++) {

                    if (blog.getCategories().get(i).equals(getResources().getString(R.string.business_chk))) {
                        binding.businessBx.setChecked(true);
                    } else if (blog.getCategories().get(i).equals(getResources().getString(R.string.productivity_chk))) {
                        binding.productivityBx.setChecked(true);
                    } else if (blog.getCategories().get(i).equals(getResources().getString(R.string.lifestyle_chk))) {
                        binding.lifestyleBx.setChecked(true);
                    } else if (blog.getCategories().get(i).equals(getResources().getString(R.string.entertainment_chk))) {
                        binding.entertainmentChkBx.setChecked(true);
                    }
                }
            }
        });
        viewModel.getBlogUpdateStatus().observe(getViewLifecycleOwner(), new Observer<BlogsUpdateStatus>() {
            @Override
            public void onChanged(BlogsUpdateStatus blogsUpdateStatus) {
                if (blogsUpdateStatus.isBlogUpdate()) {
                    Toast.makeText(getContext(), "Successfully Updated  Blog", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dataValidation() {
        if (!TextUtils.isEmpty(binding.titleEdt.getText().toString())) {
            if (!TextUtils.isEmpty(binding.desEdt.getText().toString())) {
                if (getCheckedCategories().size() > 0) {

                    Author author = new Author(Integer.valueOf(getResources().getString(R.string.author_id))
                            , getResources().getString(R.string.author_name)
                            , getResources().getString(R.string.author_avatar
                    ), getResources().getString(R.string.author_profession));


                           existingBlog.setTitle(binding.titleEdt.getText().toString());
                           existingBlog.setDescription(binding.desEdt.getText().toString());
                           existingBlog.setCategories(getCheckedCategories());
                           existingBlog.setAuthor(author);
                    viewModel.updateBlog(existingBlog);
                } else {
                    Toast.makeText(getContext(), "Please Select a Category", Toast.LENGTH_SHORT).show();
                }
            } else {
                binding.desEdt.setError("Please Enter Description");
                binding.desEdt.requestFocus();
            }
        } else {
            binding.titleEdt.setError("Please Enter Title");
            binding.titleEdt.requestFocus();
        }
    }

    private List<String> getCheckedCategories() {
        List<String> categories = new ArrayList<>();
        if (binding.businessBx.isChecked()) {
            categories.add(getResources().getString(R.string.business_chk));
        }
        if (binding.lifestyleBx.isChecked()) {
            categories.add(getResources().getString(R.string.lifestyle_chk));
        }
        if (binding.entertainmentChkBx.isChecked()) {
            categories.add(getResources().getString(R.string.entertainment_chk));
        }
        if (binding.productivityBx.isChecked()) {
            categories.add(getResources().getString(R.string.productivity_chk));
        }
        return categories;
    }
}