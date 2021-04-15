package com.moshiur.simpleblogapp.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.databinding.ActivityCreateBlogPostBinding;

import java.util.ArrayList;
import java.util.List;

public class CreateBlogPostActivity extends AppCompatActivity {

    //for multiselect items
    boolean[] selectedCategory;
    List<String> categoryList = new ArrayList<>();
    String[] allCategories = {"Lifestyle", "Business", "Entertainment", "Productivity", "Comedy"};
    private ActivityCreateBlogPostBinding activityCreateBlogPostBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCreateBlogPostBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_blog_post);

        //initialize selected category
        selectedCategory = new boolean[allCategories.length];


        activityCreateBlogPostBinding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
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
}