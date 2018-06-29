package com.healthstore.app.presenter.impl;

import com.healthstore.app.model.User;
import com.healthstore.app.presenter.UserPresenter;
import com.healthstore.app.presenter.UserService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class UserPresenterImpl implements UserPresenter {

    public void getUser() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://healthtest:8080/app/api/")
                .addConverterFactory(JacksonConverterFactory.create()).build();

        UserService userService = retrofit.create(UserService.class);
        Call<User> user = userService.getUser(1);
        User body = user.execute().body();
        System.out.println(body.getId());
        System.out.println(body.getUserName());
        System.out.println(body.getWatchword());
    }

}
