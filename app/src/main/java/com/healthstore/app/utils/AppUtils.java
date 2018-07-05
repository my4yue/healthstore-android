package com.healthstore.app.utils;

import android.content.Context;

import com.healthstore.app.App;
import com.healthstore.app.di.component.AppComponent;

public class AppUtils {

    public static AppComponent obtainAppComponentFromContext(Context ctx) {
        return ((App)ctx.getApplicationContext()).getAppComponent();
    }

}
