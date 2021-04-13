package com.moshiur.simpleblogapp.di.retrofit;

import com.moshiur.simpleblogapp.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = {RetrofitModule.class})
public interface RetrofitComponent {
    public void inject(MainActivity mainActivity);
}
