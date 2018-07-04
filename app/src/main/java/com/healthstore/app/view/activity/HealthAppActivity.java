package com.healthstore.app.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.healthstore.app.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public abstract class HealthAppActivity extends AppCompatActivity {

    abstract int getContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.my_slide_in_left, R.anim.slide_out_right)
                .replace(R.id.content_view, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStackImmediate();
    }

}
