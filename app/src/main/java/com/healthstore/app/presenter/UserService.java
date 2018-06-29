package com.healthstore.app.presenter;

import com.healthstore.app.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("user/{id}") Call<User> getUser(int id);

}
