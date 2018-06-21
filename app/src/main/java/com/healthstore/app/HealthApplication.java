package com.healthstore.app;

import android.app.Application;
import android.util.Log;

public class HealthApplication extends Application {

    private static final String TAG = HealthApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");
    }
}
