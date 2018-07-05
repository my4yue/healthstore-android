package com.healthstore.app.mvp.model;

import com.healthstore.app.di.scope.ActivityScope;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.entity.User;

import javax.inject.Inject;

import io.reactivex.Observable;

@FragmentScope
public class UserModel implements UserContract.Model {

    @Inject UserService userService;

    @Inject
    public UserModel(){}

    @Override public Observable<User> getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override public void onDestroy() {

    }

}
