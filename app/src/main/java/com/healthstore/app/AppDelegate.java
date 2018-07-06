package com.healthstore.app;

import android.app.Application;
import android.support.v4.app.FragmentManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerAppComponent;

import javax.inject.Inject;

public class AppDelegate {

    @Inject Application mApp;
    @Inject ObjectMapper mObjectMapper;
    @Inject Application.ActivityLifecycleCallbacks mAppActivityLifeCycle;

    private AppComponent mAppComponent;


    public AppDelegate(App app) {

        mAppComponent = DaggerAppComponent.builder().application(app).build();
        mAppComponent.inject(this);

        mApp.registerActivityLifecycleCallbacks(mAppActivityLifeCycle);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
