package com.moshiur.simpleblogapp.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse implements Serializable
{

    @SerializedName("blogs")
    @Expose
    private List<Blog> blogs = null;
    private final static long serialVersionUID = 4885911939044044183L;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

}