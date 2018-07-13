package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.mvp.IPresenter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;

public class AppInstructionFragment extends AppFragment<IPresenter.Empty> {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;

    @Override int layoutResId() {
        return R.layout.fragment_app_instruction;
    }

    @Override void setUpComponent(AppComponent appComponent) {

    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        topBar.setTitle("设置");
        topBar.addLeftBackImageButton().setOnClickListener(v -> mActivityManager.popupFragment());
    }
}
