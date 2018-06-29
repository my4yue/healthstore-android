package com.healthstore.app.presenter.impl;

import com.healthstore.app.presenter.UserPresenter;
import com.healthstore.app.presenter.UserService;

import retrofit2.Retrofit;

public class UserPresenterImpl implements UserPresenter {

    public void getUser(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://healthtest:8080/app/api/").build();
        UserService userService = retrofit.create(UserService.class);
        userService.getUser(1);
    }

}
