package com.healthstore.app;

import android.app.Application;
import android.support.v4.app.FragmentManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerAppComponent;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.entity.User;

import javax.inject.Inject;
import javax.inject.Named;

public class AppDelegate {

    @Inject Application mApp;
    @Inject AppManager mAppManager;
    @Inject Application.ActivityLifecycleCallbacks mAppActivityLifeCycle;

    private AppComponent mAppComponent;


    public AppDelegate(App app) {

        mAppComponent = DaggerAppComponent.builder().application(app).build();
        mAppComponent.inject(this);

        mAppManager.syncMainUser();
        mApp.registerActivityLifecycleCallbacks(mAppActivityLifeCycle);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
