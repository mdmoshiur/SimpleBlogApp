package com.moshiur.simpleblogapp.di.scopes;


import android.app.Application;

import com.moshiur.simpleblogapp.di.retrofit.DaggerRetrofitComponent;
import com.moshiur.simpleblogapp.di.retrofit.RetrofitComponent;

public class MyApplication extends Application {
    private RetrofitComponent retrofitComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        retrofitComponent = DaggerRetrofitComponent.create();
    }

    public RetrofitComponent getRetrofitComponent(){
        return retrofitComponent;
    }
}
