package com.healthstore.app.mvp.model.api;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FeedbackService {

    @FormUrlEncoded
    @POST("feedback") Observable<Response<Void>> submitFeedback(@Field("userId") long userId, @Field("content") String content);

}
