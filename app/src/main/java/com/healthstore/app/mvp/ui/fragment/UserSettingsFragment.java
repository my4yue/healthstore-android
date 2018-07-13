package com.healthstore.app.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.presenter.UserPresenter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.OnClick;

public class UserSettingsFragment extends AppFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.user_settings) QMUIGroupListView userSettings;

    @Override int layoutResId() {
        return R.layout.fragment_user_settings;
    }

    @Override void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @OnClick(R.id.btn_logout)
    public void onClickLogout(){

    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topBar.setTitle("设置");
        topBar.addLeftBackImageButton().setOnClickListener(v -> mActivityManager.popupFragment());

        QMUICommonListItemView agendaBack = userSettings.createItemView("日程背景图");
        agendaBack.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView itemSetting = userSettings.createItemView("项目设置");
        itemSetting.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView activityNotify = userSettings.createItemView("接收活动通知");
        activityNotify.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);

        QMUICommonListItemView instruction = userSettings.createItemView("使用说明");
        instruction.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView clearCache = userSettings.createItemView("清除缓存");
        clearCache.setDetailText("100K");
        clearCache.getDetailTextView().setTextColor(Color.GRAY);

        QMUICommonListItemView version = userSettings.createItemView("版本号");
        version.setDetailText("1.0.0");
        version.getDetailTextView().setTextColor(Color.GRAY);

        QMUIGroupListView.newSection(this.getContext())
                .addItemView(agendaBack, v -> {})
                .addItemView(itemSetting, v -> mActivityManager.replaceFragment(getContainerId(), new UserItemSelectorFragment()))
                .addItemView(activityNotify, v -> {})
                .addItemView(instruction, v -> { mActivityManager.replaceFragment(getContainerId(), new AppInstructionFragment()); })
                .addItemView(clearCache, v -> {})
                .addItemView(version, v -> {})
                .addTo(userSettings);

        activityNotify.getSwitch().setChecked(mAppManager.getMainUser().getValue().isReceiveActivityTrailer());
        activityNotify.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
            mPresenter.updateMainUser(new User() {
                @Override public Boolean isReceiveActivityTrailer() {
                    return isChecked;
                }
            });
        });
    }

}
