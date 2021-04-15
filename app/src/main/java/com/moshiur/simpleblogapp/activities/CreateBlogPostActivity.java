package com.moshiur.simpleblogapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.databinding.ActivityCreateBlogPostBinding;
import com.moshiur.simpleblogapp.models.Author;
import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.viewmodels.CreateBlogPostViewModel;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CreateBlogPostActivity extends AppCompatActivity {

    //for multiselect items
    boolean[] selectedCategory;
    List<String> categoryList = new ArrayList<>();
    String[] allCategories = {"Lifestyle", "Business", "Entertainment", "Productivity", "Comedy"};


    private ActivityCreateBlogPostBinding activityCreateBlogPostBinding;
    private CreateBlogPostViewModel createBlogPostViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCreateBlogPostBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_blog_post);

        //remove actionbar text
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        setTitle(R.string.create_blog_actionbar_text); // set actionbar title

        //initialize viewModel
        createBlogPostViewModel = new ViewModelProvider(this).get(CreateBlogPostViewModel.class);

        //initialize selected category
        selectedCategory = new boolean[allCategories.length];


        //show alert dialog for multi select categories
        activityCreateBlogPostBinding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        //set onclick action for create button
        activityCreateBlogPostBinding.createBlogPostCreateBlogButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBlogPost();
            }
        });

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateBlogPostActivity.this);
        builder.setTitle(R.string.select_category_text);
        builder.setCancelable(false);
        builder.setMultiChoiceItems(allCategories, selectedCategory, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                selectedCategory[i] = b;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                categoryList.clear();
                for (int j = 0; j < allCategories.length; j++) {
                    if (selectedCategory[j]) {
                        categoryList.add(allCategories[j]);
                    }
                }
                String cat = "";
                for (int k = 0; k < categoryList.size(); k++) {
                    cat += categoryList.get(k);
                    if (k != categoryList.size() - 1) {
                        cat += ", ";
                    }
                }
                Log.d("helloworld", "onClick: " + cat);
                activityCreateBlogPostBinding.textView3.setText(cat);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int j = 0; j < selectedCategory.length; j++) {
                    selectedCategory[j] = false;
                    categoryList.clear();
                    activityCreateBlogPostBinding.textView3.setText(R.string.blog_categories);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createBlogPost() {
        String title = activityCreateBlogPostBinding.createBlogPostTitleId.getText().toString().trim();
        String description = activityCreateBlogPostBinding.createBlogPostDescriptionId.getText().toString().trim();

        if (validateField(title, description)) {
            Author author = new Author();
            author.setId(1);
            author.setName("John Doe");
            author.setAvatar("https://i.pravatar.cc/250");
            author.setProfession("Content writer");

            Blog blog = new Blog();
            blog.setAuthor(author);
            blog.setCategories(categoryList);
            blog.setTitle(title);
            blog.setDescription(description);
            blog.setCoverPhoto("https://i.picsum.photos/id/608/200/300.jpg?hmac=b-eDmVyhr3rI_4k3z2J_PNwOxEwSKa5EDC9uFH4jERU");

            createBlogPostViewModel.createBlog(blog);

            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private boolean validateField(String title, String description) {
        if (title.equals("")) {
            Toasty.error(this, "Title should not be null", Toast.LENGTH_SHORT).show();
            return false;
        } else if (title.length() < 3) {
            Toasty.info(this, "Title must be at least 3 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (description.equals("")) {
            Toasty.error(this, "Description should not be null", Toast.LENGTH_SHORT).show();
            return false;
        } else if (description.length() < 10) {
            Toasty.info(this, "Description must be at least 10 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (categoryList.size() < 1) {
            Toasty.error(this, "Please select at least one category", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //handle onbackpresed menu
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