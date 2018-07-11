package com.healthstore.app.di.module;

import android.app.Application;

import com.fasterxml.jackson.annotation.JsonInclude;
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
        ObjectMapper mapper =  new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    @Singleton
    @Provides
//    @Named("user")
    static User providerMainUser() {return new User(); }

    @Binds
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(AppActivityLifeCycle appActivityLifecycle);

}
