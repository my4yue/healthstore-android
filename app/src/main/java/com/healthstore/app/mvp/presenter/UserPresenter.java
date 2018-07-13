package com.healthstore.app.mvp.presenter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.entity.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class UserPresenter implements IPresenter {

    @Inject UserContract.View mView;
    @Inject UserContract.Model mModel;
    @Inject ObjectMapper objectMapper;
    @Inject AppManager appManager;

    @Inject
    public UserPresenter() {

    }

    public void updateMainUser(User user){
        mView.showLoading();
        User mainUser = appManager.getMainUser().getValue();
        mModel.updateUser(mainUser.getId(), user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mView.hideLoading())
                .subscribe(() -> {});
    }

    @Override public void onStart() {

    }

    @Override public void onDestroy() {

    }
}
