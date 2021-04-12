package com.moshiur.simpleblogapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.adapters.BlogAdapter;
import com.moshiur.simpleblogapp.models.Blog;
import com.moshiur.simpleblogapp.models.ServerResponse;
import com.moshiur.simpleblogapp.retrofit.ApiInterface;
import com.moshiur.simpleblogapp.retrofit.RetrofitApiClient;
import com.moshiur.simpleblogapp.viewmodels.BlogViewModel;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BlogViewModel blogViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.blog_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        BlogAdapter adapter = new BlogAdapter(this);
        recyclerView.setAdapter(adapter);


        //create noteviewmodel
        blogViewModel = new ViewModelProvider(this).get(BlogViewModel.class);
        //noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        blogViewModel.getAllBlogs().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> notes) {
                //Toasty.success(MainActivity.this, "Data changed", Toasty.LENGTH_SHORT).show();
                Log.d("whoareyou", "onChanged: "+notes.size());

                adapter.submitList(notes);
            }
        });

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
                    for(int i =0; i<blogs.size(); i++){
                        blogViewModel.createBlog(blogs.get(i));
                    }

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