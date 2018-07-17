package com.healthstore.app.mvp.model.api;

import com.healthstore.app.mvp.model.entity.QnToken;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TokenService {

    @GET("qnToken") Observable<QnToken> getQiNiuUpToken();

    @FormUrlEncoded
    @POST("token") Object generateToken(@Field("code") String code);

    @GET("token/{id}") Object getTokenById(@Path("id") String id);
}
