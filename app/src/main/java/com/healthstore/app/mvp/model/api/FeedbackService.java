package com.healthstore.app.mvp.model.api;

import com.healthstore.app.mvp.model.entity.Response;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FeedbackService {

    @FormUrlEncoded
    @POST("feedback") Observable<Response> submitFeedback(@Field("userId") long userId, @Field("content") String content);

}
