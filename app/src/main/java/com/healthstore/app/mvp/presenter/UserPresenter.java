package com.healthstore.app.mvp.presenter;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.scope.ActivityScope;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.entity.User;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
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
        User mainUser = appManager.getMainUser();
        mModel.updateUser(mainUser.getId(), user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    System.out.println(response.getErrorCode());
                });
    }

    @Override public void onStart() {

    }

    @Override public void onDestroy() {

    }
}
