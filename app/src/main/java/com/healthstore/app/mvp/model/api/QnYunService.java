package com.healthstore.app.mvp.model.api;

import java.io.File;

import io.reactivex.Completable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface QnYunService {

    @Multipart
    @POST Completable uploadImage(@Part("key") String key, @Part("upload_token") String upToken, @Part File file);

}
