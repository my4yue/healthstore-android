package com.healthstore.app.mvp.model.api;

import com.healthstore.app.mvp.model.entity.User;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface UserService {

    @GET("user/{id}") Observable<User> getUserById(@Path("id") long id);

    @PATCH("user/{id}") Completable patchUser(@Path("id") long id, @Body User user);

}
