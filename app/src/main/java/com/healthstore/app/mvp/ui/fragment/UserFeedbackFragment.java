package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.healthstore.app.ActivityManager;
import com.healthstore.app.AppManager;
import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerFeedbackComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.component.UserComponent;
import com.healthstore.app.di.module.FeedbackModule;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.IView;
import com.healthstore.app.mvp.contract.FeedbackContract;
import com.healthstore.app.mvp.presenter.FeedbackPresenter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFeedbackFragment extends AppFragment<FeedbackPresenter> implements FeedbackContract.View{

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.edt_feedback) EditText editFeedback;

    @Inject ActivityManager activityManager;

    @Override int layoutResId() {
        return R.layout.fragment_feedback;
    }

    @Override void setUpComponent(AppComponent appComponent) {
        DaggerFeedbackComponent.builder()
                .appComponent(appComponent)
                .feedbackModule(new FeedbackModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topBar.setTitle(R.string.feedback_title);
        topBar.addLeftBackImageButton().setOnClickListener(v -> {
            activityManager.popupFragment();
        });
    }

    @OnClick(R.id.btn_submit)
    void onClick(View btn) {
        mPresenter.submit(editFeedback.getText().toString());
    }

    @Override public void onSubmitSuccess() {
        mAppManager.showToast("提交成功");
    }

    @Override public void onSubmitFailed() {
        mAppManager.showToast("提交失败");
    }
}
