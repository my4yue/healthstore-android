package com.healthstore.app.di.module;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppActivityLifeCycle;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static ObjectMapper providerObjectMapper(){
        return new ObjectMapper();
    }

    @Binds
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(AppActivityLifeCycle appActivityLifecycle);

}
