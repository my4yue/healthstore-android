package com.healthstore.app.utils;

import android.app.Application;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ToastManager {

    @Inject Application application;

    Toast mToast;

    @Inject
    public ToastManager() {
        mToast = Toast.makeText(application, "", Toast.LENGTH_SHORT);
    }



}
