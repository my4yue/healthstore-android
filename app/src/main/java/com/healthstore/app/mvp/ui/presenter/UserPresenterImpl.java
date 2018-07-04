package com.healthstore.app.mvp.ui.presenter;

import com.healthstore.app.mvp.ui.presenter.UserPresenter;
import com.healthstore.app.api.UserService;

import java.io.IOException;

import javax.inject.Inject;

public class UserPresenterImpl implements UserPresenter {

    @Inject
    UserService userService;

    public void getUser() throws IOException {

//        DaggerApiComponent.builder().build().inject(this);
//        System.out.println(userService.getUser(1).getClass());
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://healthtest:8080/app/api/")
//                .addConverterFactory(JacksonConverterFactory.create()).build();
//
//        UserService userService = retrofit.create(UserService.class);
//
//        Call<User> user = userService.getUser(34);
//        user.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//        User body = user.execute().body();
//        System.out.println(body.getId());
//        System.out.println(body.getUserName());
//        System.out.println(body.getWatchword());
    }

}
