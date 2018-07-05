package com.healthstore.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentController;

import com.healthstore.app.mvp.ui.fragment.MeFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppManager {

    @Inject
    public AppManager() {
    }

    public void startActivity(Context ctx, Class activityClass) {
        Intent intent = new Intent(ctx, activityClass);
        ctx.startActivity(intent);
    }

    public void startFragment(FragmentController controller, Fragment targetFragment) {
        String tag = targetFragment.getClass().getSimpleName();
        controller.getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.my_slide_in_left, R.anim.slide_out_right)
                .replace(R.id.content_view, targetFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void popFragment(FragmentController controller) {
        controller.getSupportFragmentManager().popBackStackImmediate();
    }

}
