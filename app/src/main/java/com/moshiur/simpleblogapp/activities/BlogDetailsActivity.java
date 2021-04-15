package com.moshiur.simpleblogapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.databinding.ActivityBlogDetailsBinding;
import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.utils.Constants;
import com.moshiur.simpleblogapp.viewmodels.BlogDetailsViewModel;

import es.dmoral.toasty.Toasty;

public class BlogDetailsActivity extends AppCompatActivity {

    private ActivityBlogDetailsBinding activityBlogDetailsBinding;
    private BlogDetailsViewModel blogDetailsViewModel;
    private Integer blog_id;

    private Blog blog;

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

        blog = blogDetailsViewModel.getBlogAt(blog_id);

        activityBlogDetailsBinding.setBlogItem(blog);
    }


    private void editBlog() {
        //show toast
        Toasty.info(this, R.string.edit_notice, Toasty.LENGTH_SHORT).show();

        setEditView();

        activityBlogDetailsBinding.editBlogButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEditedPost();
            }
        });

    }

    private void saveEditedPost() {
        //get edit text values
        String new_title = activityBlogDetailsBinding.editBlogDetailsTitleId.getText().toString().trim();
        String new_author_name = activityBlogDetailsBinding.editBlogDetailsAuthorNameId.getText().toString().trim();
        String new_author_profession = activityBlogDetailsBinding.editBlogDetailsAuthorProfessionId.getText().toString().trim();
        String new_description = activityBlogDetailsBinding.editBlogDetailsDescriptionId.getText().toString().trim();

        // empty check
        if (!new_title.equals("") && !new_author_name.equals("") && !new_author_profession.equals("") && !new_description.equals("")) {
            blog.setTitle(new_title);
            blog.setDescription(new_description);
            blog.getAuthor().setName(new_author_name);
            blog.getAuthor().setProfession(new_author_profession);

            //edit the database now
            blogDetailsViewModel.editBlogPost(blog);

            //refresh activity
            startActivity(new Intent(this, BlogDetailsActivity.class));

        } else {
            Toasty.error(this, "Any wouldn't be null", Toasty.LENGTH_SHORT).show();
        }
    }

    private void setEditView() {
        //set visibility gone to details activity
        activityBlogDetailsBinding.showDetailsGrp.setVisibility(View.GONE);
        //setVisibility of editable  view
        activityBlogDetailsBinding.editGrp.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.blog_details_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.edit_blog:
                editBlog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}