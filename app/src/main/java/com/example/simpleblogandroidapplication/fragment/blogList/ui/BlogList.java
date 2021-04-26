package com.example.simpleblogandroidapplication.fragment.blogList.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simpleblogandroidapplication.fragment.blogList.Adapter.BlogItemAdapter;
import com.example.simpleblogandroidapplication.fragment.blogList.viewModel.BlogListViewModel;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.R;
import com.example.simpleblogandroidapplication.databinding.FragmentBlogListBinding;

import java.util.List;


public class BlogList extends Fragment {
    private BlogListViewModel viewModel;
    private FragmentBlogListBinding binding;
    public BlogList() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBlogListBinding.inflate(inflater, container, false);

        return binding.getRoot();



    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BlogListViewModel.class);
        binding.addBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_blog_list_to_add_blog);
            }
        });
        Observer();


       /* String title= requireArguments().getString(ACTIONBAR_TITLE,null);
        if(title!=null){
            // Navigation.findNavController(view).getCurrentDestination().setLabel(title);
            ((MainActivity) requireActivity()).updateToolbar(title);

        }*/
}
public void Observer(){
        viewModel.getBlogsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
                if(blogs!=null) {
                    binding.blogListRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    binding.blogListRv.setHasFixedSize(true);
                    BlogItemAdapter adapter = new BlogItemAdapter(blogs, getContext());
                    binding.blogListRv.setAdapter(adapter);
                    Log.e("testt", blogs.toString());
                }
            }
        });
}
}