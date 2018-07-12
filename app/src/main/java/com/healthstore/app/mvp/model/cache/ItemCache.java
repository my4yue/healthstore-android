package com.healthstore.app.mvp.model.cache;

import com.healthstore.app.mvp.model.entity.Item;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemCache {

    @Inject public ItemCache(){}

    Map<String, Item> mapCache = new HashMap<>();



}
