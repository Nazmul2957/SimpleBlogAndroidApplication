package com.example.simpleblogandroidapplication.fragment.blogDetails.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.simpleblogandroidapplication.fragment.blogDetails.viewModel.BlogDetailsViewModel;
import com.example.simpleblogandroidapplication.model.Blog;
import com.example.simpleblogandroidapplication.R;
import com.example.simpleblogandroidapplication.util.Constants;
import com.example.simpleblogandroidapplication.databinding.FragmentBlogDetailsBinding;


public class BlogDetails extends Fragment {

    private BlogDetailsViewModel viewModel;
    private FragmentBlogDetailsBinding binding;
    public BlogDetails() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlogDetailsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BlogDetailsViewModel.class);
        viewModel.BlogDetails(getArguments());
        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Bundle bundle=new Bundle();
            String blogId=getArguments().getString(Constants.BLOGID);
                bundle.putString(Constants.BLOGID,String.valueOf(blogId));
                Navigation.findNavController(view).navigate(R.id.action_blog_details_to_blog_edit,bundle);

            }
        });
        Observer();


       /* String title= requireArguments().getString(ACTIONBAR_TITLE,null);
        if(title!=null){
            // Navigation.findNavController(view).getCurrentDestination().setLabel(title);
            ((MainActivity) requireActivity()).updateToolbar(title);

        }*/
    }

    private void Observer() {
        viewModel.getStatus().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        viewModel.Blog().observe(getViewLifecycleOwner(), new Observer<Blog>() {
            @Override
            public void onChanged(Blog blog) {
                binding.titleValue.setText(blog.getTitle());
                binding.desValue.setText(blog.getDescription());
                binding.authorNameValue.setText(blog.getAuthor().getName());
                binding.authorProfessionValue.setText(blog.getAuthor().getProfession());
                String categories="";
                for(int i=0;i<blog.getCategories().size();i++){
                    //categories.concat(" "+blog.getCategories().get(i));
                    categories=categories+""+blog.getCategories().get(i)+"\n";
                }


                binding.catsValue.setText(categories);
                Glide.with(getContext())
                        .load(blog.getCoverPhoto())
                        .placeholder(R.drawable.placeholder)
                        .into(binding.coverPhoto);
                Glide.with(getContext())
                        .load(blog.getAuthor().getAvatar())
                        .placeholder(R.drawable.placeholder)
                        .into(binding.authorImg);
            }
        });
    }
}