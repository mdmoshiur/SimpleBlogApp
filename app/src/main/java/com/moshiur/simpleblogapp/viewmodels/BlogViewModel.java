package com.moshiur.simpleblogapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.repositories.BlogRepository;

import java.util.List;

public class BlogViewModel extends AndroidViewModel {

    private BlogRepository blogRepository;
    private LiveData<List<Blog>> allBlogs;

    public BlogViewModel(@NonNull Application application) {
        super(application);
        this.blogRepository = new BlogRepository(application);
        this.allBlogs = blogRepository.getAllBlogs();
    }

    public void createBlog(Blog blog) {
        blogRepository.createBlogPost(blog);
    }

    public void editBlog(Blog blog) {
        blogRepository.editBlogPost(blog);
    }

    public LiveData<List<Blog>> getAllBlogs(){
        return allBlogs;
    }
}
