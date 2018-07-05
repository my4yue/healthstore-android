package com.healthstore.app;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerAppComponent;

import javax.inject.Inject;

public class AppDelegate {

    @Inject App mApp;
    @Inject ObjectMapper mObjectMapper;

    private AppComponent mAppComponent;

    public AppDelegate(App app) {

        mAppComponent = DaggerAppComponent.builder().application(app).build();
        mAppComponent.inject(this);

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
