package com.healthstore.app.di.module;

import android.app.Application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppActivityLifeCycle;
import com.healthstore.app.mvp.model.entity.User;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    ObjectMapper providerObjectMapper(){
        ObjectMapper mapper =  new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    @Singleton
    @Provides
    Application provideApplication(){
        return mApplication;
    }

    @Singleton
    @Provides
    Application.ActivityLifecycleCallbacks provideActivityLifecycle(AppActivityLifeCycle appActivityLifecycle){
        return appActivityLifecycle;
    }

}
