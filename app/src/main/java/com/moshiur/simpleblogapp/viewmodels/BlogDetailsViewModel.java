package com.moshiur.simpleblogapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.repositories.BlogRepository;

public class BlogDetailsViewModel extends AndroidViewModel {

    private BlogRepository blogRepository;

    public BlogDetailsViewModel(@NonNull Application application) {
        super(application);
        this.blogRepository = new BlogRepository(application);
    }

    public Blog getBlogAt(Integer id) {
        return blogRepository.getBlogPostAt(id);
    }

    public void editBlogPost(Blog blog) {
        blogRepository.editBlogPost(blog);
    }
}
