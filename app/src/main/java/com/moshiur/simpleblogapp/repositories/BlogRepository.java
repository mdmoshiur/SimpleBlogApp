package com.moshiur.simpleblogapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.room.BlogDao;
import com.moshiur.simpleblogapp.room.BlogDatabase;

import java.util.List;

public class BlogRepository {

    private BlogDao blogDao;
    private LiveData<List<Blog>> allBlogs;

    public BlogRepository(Application application){
        BlogDatabase database = BlogDatabase.getInstance(application);
        blogDao = database.blogDao();
        allBlogs = blogDao.getAllBLogs();
    }

    public void createBlogPost(Blog blog){
        new CreateBlogPostAsyncTask(blogDao).execute(blog);
    }

    public void editBlogPost(Blog blog) {
        new EditBlogPostAsyncTask(blogDao).execute(blog);
    }

    public LiveData<List<Blog>> getAllBlogs() {
        return allBlogs;
    }


    private static class CreateBlogPostAsyncTask extends AsyncTask<Blog, Void, Void> {
        private BlogDao blogDao;
        private CreateBlogPostAsyncTask(BlogDao blogDao) {
            this.blogDao = blogDao;
        }
        @Override
        protected Void doInBackground(Blog... blogs) {
            blogDao.createBlog(blogs[0]);
            return null;
        }
    }
    private static class EditBlogPostAsyncTask extends AsyncTask<Blog, Void, Void> {
        private BlogDao blogDao;
        private EditBlogPostAsyncTask(BlogDao blogDao) {
            this.blogDao = blogDao;
        }
        @Override
        protected Void doInBackground(Blog... blogs) {
            blogDao.editBlog(blogs[0]);
            return null;
        }
    }
}
