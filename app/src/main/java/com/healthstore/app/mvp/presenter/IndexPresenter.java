package com.healthstore.app.mvp.presenter;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.scope.ActivityScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.IndexContract;
import com.healthstore.app.mvp.model.api.ItemService;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.cache.ItemCache;
import com.healthstore.app.mvp.model.entity.Item;
import com.healthstore.app.mvp.model.entity.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class IndexPresenter implements IPresenter {

    @Inject IndexContract.View view;
    //    @Inject IndexContract.Model model;
    @Inject UserService userService;
    @Inject ItemService itemService;
    @Inject AppManager appManager;
    @Inject ItemCache itemCache;
    @Inject ObjectMapper objectMapper;

    @Inject
    public IndexPresenter() {
    }

    public void initData() {
        Observable<User> obUser = userService.getUserById(1);
        Observable<List<Item>> obItems = itemService.getItems(null);
        Observable
                .zip(obUser, obItems, (user, items) -> {
                    itemCache.updateCache(items);
                    return user;
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(v -> view.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> view.hideLoading())
                .subscribe(user -> {
                    System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));;
                    appManager.getMainUser().setValue(user);
                    view.hideLoading();
                });
    }

    @Override public void onStart() {

    }

    @Override public void onDestroy() {

    }
}
