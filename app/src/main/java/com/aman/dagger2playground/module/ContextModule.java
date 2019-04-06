package com.aman.dagger2playground.module;

import android.content.Context;

import com.aman.dagger2playground.interfaces.ApplicationContext;
import com.aman.dagger2playground.interfaces.RandomUserApplicationScope;

import dagger.Module;
import dagger.Provides;


@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
