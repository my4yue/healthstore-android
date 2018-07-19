package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.presenter.UserPresenter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;

public class UserWatchWordFragment extends AppFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.ev_content) EditText editText;

    @Override int layoutResId() {
        return R.layout.fragment_user_watchword;
    }

    @Override void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder().appComponent(appComponent).userModule(new UserModule(this, getActivity())).build().inject(this);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        topBar.setTitle(R.string.watchword_settings_title);
        topBar.addLeftBackImageButton().setOnClickListener(v->{mActivityManager.popupFragment();});
        topBar.addRightTextButton(R.string.confirm, R.id.btn_save_user).setOnClickListener((View v) ->{
            String text =editText.getText().toString();
            if (text.length() <=0 && text.length() > 15) {
                mAppManager.showToast(getContext().getString(R.string.watchword_settings_hint));
                return;
            }

            User user = new User();
            user.setWatchword(text);
            mPresenter.updateMainUser(user);
        });

    }

    @Override public void onUserUpdated(User user) {
        mActivityManager.popupFragment();
    }
}
