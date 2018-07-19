package com.healthstore.app.mvp.presenter;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.api.TokenService;
import com.healthstore.app.mvp.model.entity.QnToken;
import com.healthstore.app.mvp.model.entity.User;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class UserPresenter implements IPresenter {

    @Inject
    UserContract.View mView;
    @Inject
    UserContract.Model mModel;
    @Inject
    ObjectMapper objectMapper;
    @Inject
    AppManager appManager;

    @Inject
    TokenService tokenService;

    @Inject
    public UserPresenter() {

    }

    public void updateMainUser(User user) {
        mView.showLoading();
        User mainUser = appManager.getMainUser().getValue();
        mModel.updateUser(mainUser.getId(), user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mView.hideLoading())
                .doOnComplete(()->{
                    mainUser.merge(user);
                    appManager.getMainUser().setValue(mainUser);})
                .subscribe(() -> {
//                    mView.onUserUpdated();
                });
    }

    public void uploadPicture(File file) {
        tokenService.getQiNiuUpToken()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(d -> mView.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(qnToken -> qnToken.getUpToken())
//                .doFinally(() -> mView.hideLoading())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(token -> {
                    UploadManager uploadManager = new UploadManager();
                    String upKey = file.getName();
                    String url = "http://omsss06f1.bkt.clouddn.com/" + upKey;
                    uploadManager.put(file, upKey, token, (key, info, response) -> {
                        if (info.isOK()) {
                            Log.d("UserPresenter-uploadPicture", " success");
                            Log.d("UserPresenter-uploadPicture", "updating user");
                            User user = new User();
                            user.setAgendaBackgroundImageUrl(url);
                            updateMainUser(user);
                        } else {
                            Log.d("UserPresenter", "uploadPicture failed");
                            Log.d("UserPresenter", info.toString());
                            mView.hideLoading();
                        }
                    }, null);
                });

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
