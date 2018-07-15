package com.healthstore.app.mvp.presenter;

import com.healthstore.app.AppManager;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.PictureContract;
import com.healthstore.app.mvp.model.api.PicService;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.entity.Picture;
import com.healthstore.app.mvp.model.entity.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class PicturePresenter implements IPresenter {

    @Inject
    PictureContract.View view;
    @Inject
    PicService picService;
    @Inject
    UserService userService;
    @Inject
    AppManager appManager;

    @Inject
    public PicturePresenter() {
    }

    public void loadPicture() {
        picService.getPictureList(0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pictures -> {
                    view.onLoadDone(pictures.getContent());
                });
    }

    public void selectPicture(Picture picture) {
        User mainUser = appManager.getMainUser().getValue();
        User patchUser = new User();
        patchUser.setAgendaBackgroundImageUrl(picture.getPicUrl());
        userService.patchUser(mainUser.getId(), patchUser)
                .subscribeOn(Schedulers.io())
                .doFinally(()-> view.hideLoading())
                .doOnSubscribe(disposable -> view.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()-> mainUser.setAgendaBackgroundImageUrl(picture.getPicUrl()));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }

}
