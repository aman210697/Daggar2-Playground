package com.aman.dagger2playground.application;

import android.app.Activity;
import android.app.Application;


import com.aman.dagger2playground.component.DaggerRandomUserComponent;
import com.aman.dagger2playground.component.RandomUserComponent;
import com.aman.dagger2playground.module.ContextModule;

import timber.log.Timber;


public class RandomUserApplication extends Application {

    //add application name in Manifest file
    private RandomUserComponent randomUserApplicationComponent;

    public static RandomUserApplication get(Activity activity){
        return (RandomUserApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        randomUserApplicationComponent = DaggerRandomUserComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public RandomUserComponent getRandomUserApplicationComponent(){
        return randomUserApplicationComponent;
    }
}
