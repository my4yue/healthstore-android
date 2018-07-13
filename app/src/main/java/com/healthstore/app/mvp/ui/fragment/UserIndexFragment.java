package com.healthstore.app.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.presenter.UserPresenter;
import com.healthstore.app.mvp.ui.adapter.UserFuncIndexAdapter;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserIndexFragment extends AppFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.icon) QMUIRadiusImageView iconView;
    @BindView(R.id.tv_user_name) TextView tvUserName;

    @Inject UserFuncIndexAdapter userAdapter;

    @Override int layoutResId() {
        return R.layout.fragment_user_index;
    }

    @Override void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAppManager.getMainUser().observe(this, user -> {
            mImageLoader.load(getContext(), user.getIconUrl(), iconView);
            tvUserName.setText(user.getUserName());
        });

        iconView.setOnClickListener(v -> mActivityManager.replaceFragment(mContainerId, new UserDetailsFragment()));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(userAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

}
