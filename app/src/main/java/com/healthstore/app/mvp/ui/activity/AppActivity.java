package com.healthstore.app.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.healthstore.app.AppManager;
import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.utils.AppUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AppActivity<P extends IPresenter> extends AppCompatActivity {

    abstract int contentViewId();

    abstract int layoutId();

    abstract void setupActivityComponent(AppComponent appComponent);

    private static final String TAG = AppActivity.class.getSimpleName();

    private Unbinder mUnbinder;

    @Inject AppManager mAppManager;
    @Inject P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        if (layoutId() != 0) {
            setContentView(layoutId());
            mUnbinder = ButterKnife.bind(this);
        }

        setupActivityComponent(AppUtils.obtainAppComponentFromContext(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

}
