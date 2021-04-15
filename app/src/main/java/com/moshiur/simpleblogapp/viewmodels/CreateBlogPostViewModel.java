package com.moshiur.simpleblogapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.repositories.BlogRepository;

public class CreateBlogPostViewModel extends AndroidViewModel {

    private BlogRepository blogRepository;

    public CreateBlogPostViewModel(@NonNull Application application) {
        super(application);
        this.blogRepository = new BlogRepository(application);
    }

    public void createBlog(Blog blog) {
        blogRepository.createBlogPost(blog);
    }
}
