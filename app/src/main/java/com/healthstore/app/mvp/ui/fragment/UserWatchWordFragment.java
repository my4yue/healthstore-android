package com.healthstore.app.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.presenter.UserPresenter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

        topBar.setTitle("口号");
        topBar.addLeftBackImageButton().setOnClickListener(v->{mActivityManager.popupFragment();});
        topBar.addRightTextButton(R.string.save, R.id.btn_save_user).setOnClickListener((View v) ->{
            String text =editText.getText().toString();
            if (text.length() <=0 && text.length() > 15) {
                mAppManager.showToast("口号最多15个字");
                return;
            }

            User user = new User();
            user.setWatchword(text);
            mPresenter.updateMainUser(user);
//            Observable.fromArray().doOnSubscribe(d -> mPresenter.updateMainUser(user)).subscribe(d->mActivityManager.popupFragment());
        });

    }
}
