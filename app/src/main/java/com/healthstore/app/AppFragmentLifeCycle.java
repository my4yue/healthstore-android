package com.healthstore.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppFragmentLifeCycle extends FragmentManager.FragmentLifecycleCallbacks {

    @Inject
    public AppFragmentLifeCycle(){

    }

    @Override public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState);
    }
}
