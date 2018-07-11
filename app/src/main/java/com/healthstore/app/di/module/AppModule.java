package com.healthstore.app.di.module;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppActivityLifeCycle;
import com.healthstore.app.mvp.model.entity.User;

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

    @Singleton
    @Provides
//    @Named("user")
    static User providerMainUser() {return new User(); }

    @Binds
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(AppActivityLifeCycle appActivityLifecycle);

}
