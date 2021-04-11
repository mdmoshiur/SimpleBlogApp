package com.moshiur.simpleblogapp.room;

import android.widget.ListAdapter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moshiur.simpleblogapp.models.Author;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter implements Serializable {

    @TypeConverter
    public String fromAuthor(Author author) {
        if (author == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Author>() {}.getType();
        String json = gson.toJson(author, type);
        return json;
    }

    @TypeConverter
    public Author toAuthor(String authorString) {
        if (authorString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Author>() {}.getType();
        Author author = gson.fromJson(authorString, type);
        return author;
    }

    @TypeConverter
    public String fromCategories(List<String> categories){
        if (categories == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        String json = gson.toJson(categories, type);
        return json;
    }

    @TypeConverter
    public List<String> toCategories(String categoriesListString){
        if (categoriesListString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> categories = gson.fromJson(categoriesListString, type);
        return categories;
    }
}
