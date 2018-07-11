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
import com.healthstore.app.ImageLoader;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.IView;
import com.healthstore.app.utils.AppUtils;
import com.healthstore.app.utils.TipUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AppFragment<P extends IPresenter> extends Fragment implements IView{

    protected final String TAG = this.getClass().getSimpleName();

    @Inject AppManager mAppManager;
    @Inject ImageLoader mImageLoader;
    @Inject ActivityManager mActivityManager;
    @Inject P mPresenter;
    QMUITipDialog loadingTipDialog;

    int mContainerId;
    Unbinder mUnbinder;

    abstract int layoutResId();
    abstract void setUpComponent(AppComponent appComponent);

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mContainerId = container.getId();

        if (layoutResId() != 0) {
            view = inflater.inflate(layoutResId(), null);
            mUnbinder = ButterKnife.bind(this, view);
        }

        setUpComponent(AppUtils.obtainAppComponentFromContext(getContext()));
        loadingTipDialog = TipUtils.provideLoadingTip(getContext());
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
        TipUtils.showTipDialog(getContext(), message);
    }

    @Override public void showLoading() {
        loadingTipDialog.show();
    }

    @Override public void hideLoading() {
        loadingTipDialog.dismiss();
    }
}
