package com.moshiur.simpleblogapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.databinding.ActivityBlogDetailsBinding;
import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.utils.Constants;
import com.moshiur.simpleblogapp.viewmodels.BlogDetailsViewModel;

public class BlogDetailsActivity extends AppCompatActivity {

    private ActivityBlogDetailsBinding activityBlogDetailsBinding;
    private BlogDetailsViewModel blogDetailsViewModel;
    private Integer blog_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBlogDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_blog_details);

        //remove actionbar text
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        setTitle(R.string.details_actionbar_text); // set actionbar title

        //receive intent data
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.BLOG_ID)) {
            blog_id = intent.getIntExtra(Constants.BLOG_ID, -1);
        }

        blogDetailsViewModel = new ViewModelProvider(this).get(BlogDetailsViewModel.class);

        Blog blog = blogDetailsViewModel.getBlogAt(blog_id);

        activityBlogDetailsBinding.setBlogItem(blog);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}