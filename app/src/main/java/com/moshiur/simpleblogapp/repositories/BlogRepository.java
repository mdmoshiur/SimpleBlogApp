package com.moshiur.simpleblogapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.room.BlogDao;
import com.moshiur.simpleblogapp.room.BlogDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BlogRepository {

    private BlogDao blogDao;
    private LiveData<List<Blog>> allBlogs;

    public BlogRepository(Application application){
        BlogDatabase database = BlogDatabase.getInstance(application);
        blogDao = database.blogDao();
        allBlogs = blogDao.getAllBLogs();
    }

    public void createBlogPost(Blog blog) {
        new CreateBlogPostAsyncTask(blogDao).execute(blog);
    }

    public void editBlogPost(Blog blog) {
        new EditBlogPostAsyncTask(blogDao).execute(blog);
    }

    public void deleteBlogPost(Blog blog) {
        new DeleteBlogPostAsyncTask(blogDao).execute(blog);
    }

    public Blog getBlogPostAt(int id) {
        Blog blog = null;
        try {
            blog = new GetBlogPostAtAsyncTask(blogDao).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return blog;
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

    private static class DeleteBlogPostAsyncTask extends AsyncTask<Blog, Void, Void> {
        private BlogDao blogDao;

        private DeleteBlogPostAsyncTask(BlogDao blogDao) {
            this.blogDao = blogDao;
        }

        @Override
        protected Void doInBackground(Blog... blogs) {
            blogDao.deleteBlog(blogs[0]);
            return null;
        }
    }

    private class GetBlogPostAtAsyncTask extends AsyncTask<Integer, Void, Blog> {
        private BlogDao blogDao;

        private GetBlogPostAtAsyncTask(BlogDao blogDao) {
            this.blogDao = blogDao;
        }

        @Override
        protected Blog doInBackground(Integer... integers) {
            return blogDao.getBlogFromBlogID(integers[0]);
        }

    }
}
