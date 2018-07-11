package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.healthstore.app.ActivityManager;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.IView;
import com.healthstore.app.utils.AppUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AppFragment<P extends IPresenter> extends Fragment implements IView{

    protected final String TAG = this.getClass().getSimpleName();

    @Inject AppManager mAppManager;
    @Inject ActivityManager mActivityManager;
    @Inject P mPresenter;

    int mContainerId;
    Unbinder mUnbinder;

    abstract int layoutResId();
    abstract void setUpComponent(AppComponent appComponent);

    void initData(@Nullable Bundle savedInstanceState){}

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mContainerId = container.getId();

        if (layoutResId() != 0) {
            view = inflater.inflate(layoutResId(), null);
            mUnbinder = ButterKnife.bind(this, view);
        }

        setUpComponent(AppUtils.obtainAppComponentFromContext(this.getContext()));

        initData(savedInstanceState);
        return view;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();

        mUnbinder = null;

        if (mPresenter != null);
            mPresenter.onDestroy();
        mPresenter = null;

    }

    public int getContainerId() {
        return mContainerId;
    }

    @Override public void showMessage(@NonNull String message) {
        mAppManager.showToast(message);
    }

    @Override public void showLoading() {

    }

    @Override public void hideLoading() {

    }
}
