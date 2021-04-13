package com.moshiur.simpleblogapp.di.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moshiur.simpleblogapp.utils.Constants.BASE_URL;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public Gson provideGson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitApiClient(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
