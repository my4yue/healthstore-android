package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.IndexContract;
import com.healthstore.app.mvp.contract.UserContract;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;

public class AppInstructionFragment extends AppFragment<IPresenter.Empty> implements UserContract.View {

    @BindView(R.id.top_bar)
    QMUITopBarLayout topBar;

    @Override
    int layoutResId() {
        return R.layout.fragment_app_instruction;
    }

    @Override
    void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        topBar.setTitle("设置");
        topBar.addLeftBackImageButton().setOnClickListener(v -> mActivityManager.popupFragment());
    }
}
