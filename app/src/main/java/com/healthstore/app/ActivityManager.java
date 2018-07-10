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

    public void replaceFragment(AppFragment to) {
        this.replaceFragment(to, true, true);
    }

    public void replaceFragment(AppFragment to, boolean useAnim, boolean addBackStack) {
        String tag = to.getClass().getSimpleName();
        FragmentTransaction tx = mAppActivity.getSupportFragmentManager().beginTransaction();
        tx.replace(to.getContainerId(), to, tag);

        if (useAnim)
            tx = tx.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.my_slide_in_left, R.anim.slide_out_right);
        if (addBackStack)
            tx =tx.addToBackStack(tag);
        tx.commit();
    }

    void addFragment(AppFragment to) {
        FragmentTransaction tx = mAppActivity.getSupportFragmentManager().beginTransaction();
        tx.add(to.getContainerId(), to);
        tx.commit();
    }

    void removeFragment(AppFragment fragment) {

    }

    public void popupFragment() {
        mAppActivity.getSupportFragmentManager().popBackStackImmediate();
    }

}
