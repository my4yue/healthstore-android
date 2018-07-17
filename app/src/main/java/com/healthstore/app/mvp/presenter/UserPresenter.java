package com.healthstore.app.mvp.presenter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.api.TokenService;
import com.healthstore.app.mvp.model.entity.User;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class UserPresenter implements IPresenter {

    @Inject UserContract.View mView;
    @Inject UserContract.Model mModel;
    @Inject ObjectMapper objectMapper;
    @Inject AppManager appManager;

    @Inject TokenService tokenService;

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
                .subscribe(() -> {
                });
    }

    public void uploadPicture(File file) {
        tokenService.getQiNiuUpToken()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(token -> {
                    UploadManager uploadManager = new UploadManager();
                    uploadManager.put(file, "", "", (key, info, response) -> {

                    }, null);
                });

    }

    @Override public void onStart() {

    }

    @Override public void onDestroy() {

    }
}
