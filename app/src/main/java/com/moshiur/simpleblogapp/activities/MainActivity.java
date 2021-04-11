package com.moshiur.simpleblogapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.models.ServerResponse;
import com.moshiur.simpleblogapp.retrofit.ApiInterface;
import com.moshiur.simpleblogapp.retrofit.RetrofitApiClient;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeNetworkCall();

    }

    //make a request for data
    private void makeNetworkCall(){
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Call<ServerResponse> call = apiInterface.getBlogs();

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                Toasty.info(MainActivity.this, "I am in on response", Toasty.LENGTH_SHORT).show();
                ServerResponse serverResponse = response.body();
                if(response.code() == 200 && serverResponse != null){
//                    totalPages = serverResponse.getTotalPages();
//                    results = serverResponse.getResults();
//                    setMoviesRecyclerView();

                    List<Blog> blogs = serverResponse.getBlogs();
                    // moviesRecyclerViewAdapter.notifyDataSetChanged();
                    Toasty.info(MainActivity.this, "I am in okk"+ blogs.size() , Toasty.LENGTH_SHORT).show();
                    Log.d("on response", "onResponse: "+ blogs.size());
                } else {
                    Log.d("on response", "onResponse: "+ "i am in else statement");
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("on response", "onFailure: ");
            }
        });
    }
}