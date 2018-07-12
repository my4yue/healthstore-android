package com.healthstore.app.mvp.model.api;

import com.healthstore.app.mvp.model.entity.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ItemService {

    @GET("item/{id}") Observable<Item> getItemById(@Path("id") String itemId);

    @GET("item") Observable<List<Item>> getItems(@Query("itemids") String itemIds);

}
