package com.healthstore.app.mvp.model.cache;

import com.healthstore.app.mvp.model.entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemCache {

    @Inject public ItemCache(){}

    Map<String, Item> mapCache = new HashMap<>();

    public void updateCache(List<Item> items) {
        items.forEach(item -> mapCache.put(item.getItemId(), item));
    }

    public Item getItemById(String id){
        return mapCache.get(id);
    }

    public List<Item> getItemList(){
        return new ArrayList<>(mapCache.values());
    }

}
