package com.healthstore.app.mvp.model.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class User implements Entity {

    Long id;
    String userName;
    @JsonProperty("watchWord")
    String watchword;
    String wechatOpenId;
    String wechatUnionId;
    String iconUrl;
    Gender gender;
    String district;
    String agendaBackgroundImageUrl;
    Boolean isVip;
    Boolean receiveActivityTrailer;
    String imUserId;
    @JsonSerialize(using = AttentionItemSerializer.class)
    List<Item> attentionItems;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWatchword() {
        return watchword;
    }

    public void setWatchword(String watchword) {
        this.watchword = watchword;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public String getWechatUnionId() {
        return wechatUnionId;
    }

    public void setWechatUnionId(String wechatUnionId) {
        this.wechatUnionId = wechatUnionId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAgendaBackgroundImageUrl() {
        return agendaBackgroundImageUrl;
    }

    public void setAgendaBackgroundImageUrl(String agendaBackgroundImageUrl) {
        this.agendaBackgroundImageUrl = agendaBackgroundImageUrl;
    }

    public Boolean isVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public Boolean isReceiveActivityTrailer() {
        return receiveActivityTrailer;
    }

    public void setReceiveActivityTrailer(Boolean receiveActivityTrailer) {
        this.receiveActivityTrailer = receiveActivityTrailer;
    }

    public String getImUserId() {
        return imUserId;
    }

    public void setImUserId(String imUserId) {
        this.imUserId = imUserId;
    }

    public List<Item> getAttentionItems() {
        return attentionItems;
    }

    private static class AttentionItemSerializer extends JsonSerializer<List<Item>> {
        @Override
        public void serialize(List<Item> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String itemIds = value.stream().map(item -> item.getItemId() + ",").collect(Collectors.joining());
            itemIds = itemIds.substring(0, itemIds.lastIndexOf(","));
            gen.writeString(itemIds);
        }
    }

    private static class WatchwordSerializer extends JsonSerializer<String> {
        @Override public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString("watchWord");
        }
    }

    public void merge(User user) {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(user);
                if (obj != null) field.set(this, obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }


}
