package com.example.simpleblogandroidapplication.fragment.blogList.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.example.simpleblogandroidapplication.R;
import com.example.simpleblogandroidapplication.model.Blog;

import com.example.simpleblogandroidapplication.databinding.ItemOfBlogListBinding;
import com.example.simpleblogandroidapplication.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class BlogItemAdapter extends RecyclerView.Adapter<BlogItemAdapter.BlogItemViewHolder> {
    List<Blog> blogs = new ArrayList<>();
    Context context;

    public BlogItemAdapter(List<Blog> blogs, Context context) {
        this.blogs = blogs;
        this.context = context;
    }

    @NonNull
    @Override
    public BlogItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater  layoutInflater= LayoutInflater.from(parent.getContext());
        ItemOfBlogListBinding itemOfBlogListBinding=ItemOfBlogListBinding.inflate(layoutInflater,parent,false);
        return new BlogItemViewHolder(itemOfBlogListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogItemViewHolder holder, int position) {
        Blog blog = blogs.get(position);
         holder.itemOfBlogListBinding.setBlog(blog);
         holder.itemOfBlogListBinding.executePendingBindings();
        holder.itemOfBlogListBinding.coverPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString(Constants.BLOGID,String.valueOf(blog.getId()));
                Navigation.findNavController(view).navigate(R.id.action_blog_list_to_blog_details,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class BlogItemViewHolder extends RecyclerView.ViewHolder {
        ItemOfBlogListBinding itemOfBlogListBinding;

        public BlogItemViewHolder(@NonNull ItemOfBlogListBinding itemOfBlogListBinding) {
            super(itemOfBlogListBinding.getRoot());

        this.itemOfBlogListBinding=itemOfBlogListBinding;

        }
    }
}
