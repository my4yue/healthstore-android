package com.healthstore.app.mvp.model.api;

import com.healthstore.app.mvp.model.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("user/{id}") Call<User> getUser(@Path("id") int id);

}
