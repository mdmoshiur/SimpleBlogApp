package com.moshiur.simpleblogapp.retrofit;


import com.moshiur.simpleblogapp.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("/sohel-cse/simple-blog-api/db")
    Call<ServerResponse> getBlogs();

}