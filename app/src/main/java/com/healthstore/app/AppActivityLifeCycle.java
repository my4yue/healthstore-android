package com.healthstore.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppActivityLifeCycle implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = AppActivityLifeCycle.class.getSimpleName();

    @Inject
    AppManager appManager;

    @Inject
    public AppActivityLifeCycle() {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "AppActivityLifeCycle - onActivityCreated");
        Log.d(TAG, "appManager is " + (appManager == null ? "null" : "not null"));
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "AppActivityLifeCycle - onActivityStarted");

    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "AppActivityLifeCycle - onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, "AppActivityLifeCycle - onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG, "AppActivityLifeCycle - onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.d(TAG, "AppActivityLifeCycle - onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, "AppActivityLifeCycle - onActivityDestroyed");
    }
}
