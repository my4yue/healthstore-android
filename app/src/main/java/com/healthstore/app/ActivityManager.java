package com.healthstore.app;

import android.support.v4.app.FragmentTransaction;

import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.fragment.AppFragment;

import javax.inject.Inject;

@FragmentScope
public class ActivityManager {

    @Inject AppActivity mAppActivity;

    @Inject public ActivityManager(){
    }

    public void replaceFragment(int containerId, AppFragment to) {
        this.replaceFragment(containerId, to, true, true);
    }

    public void replaceFragment(int containerId, AppFragment to, boolean useAnim, boolean addBackStack) {
        String tag = to.getClass().getSimpleName();
        FragmentTransaction tx = mAppActivity.getSupportFragmentManager().beginTransaction();

        if (useAnim)
           tx.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.my_slide_in_left, R.anim.slide_out_right);

        tx.replace(containerId, to, tag);

        if (addBackStack)
            tx.addToBackStack(tag);
        tx.commit();
    }

    void addFragment(int containerId, AppFragment to) {
        FragmentTransaction tx = mAppActivity.getSupportFragmentManager().beginTransaction();
        tx.add(containerId, to);
        tx.commit();
    }

    void removeFragment(AppFragment fragment) {

    }

    public void popupFragment() {
        mAppActivity.getSupportFragmentManager().popBackStackImmediate();
    }

}
