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
import com.healthstore.app.mvp.IView;
import com.healthstore.app.utils.AppUtils;
import com.healthstore.app.utils.TipUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Optional;
import butterknife.Unbinder;

public abstract class AppActivity<P extends IPresenter> extends AppCompatActivity implements IView{

    abstract int contentViewId();

    abstract int layoutId();

    abstract void setupActivityComponent(AppComponent appComponent);

    private static final String TAG = AppActivity.class.getSimpleName();

    private Unbinder mUnbinder;

    QMUITipDialog loadingTipDialog;

    @Inject AppManager mAppManager;
    @Inject @Nullable P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        if (layoutId() != 0) {
            setContentView(layoutId());
            mUnbinder = ButterKnife.bind(this);
        }

        setupActivityComponent(AppUtils.obtainAppComponentFromContext(this));
        loadingTipDialog = TipUtils.provideLoadingTip(this);

        QMUIStatusBarHelper.translucent(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null)
            mUnbinder.unbind();
        mUnbinder = null;

        if (mPresenter != null)
            mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override public void showLoading() {
        loadingTipDialog.show();
    }

    @Override public void hideLoading() {
        loadingTipDialog.dismiss();
    }

}
