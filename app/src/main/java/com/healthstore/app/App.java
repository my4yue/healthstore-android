package com.healthstore.app;

import android.app.Application;
import android.util.Log;

import com.healthstore.app.di.component.AppComponent;

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    private AppDelegate mAppDelegate;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

        mAppDelegate = new AppDelegate(this);
    }

    public AppComponent getAppComponent() {
        return mAppDelegate.getAppComponent();
    }
}
