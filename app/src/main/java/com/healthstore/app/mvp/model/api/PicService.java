package com.healthstore.app.mvp.model.api;

import com.healthstore.app.mvp.model.entity.Page;
import com.healthstore.app.mvp.model.entity.Picture;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PicService {

    @GET("pic")
    Observable<Page<Picture>> getPictureList(@Query("page") int page, @Query("pageNum")int pageNum);

}
