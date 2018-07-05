package com.healthstore.app.mvp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User extends BaseResponse {

    long id;
    String userName;
    String watchword;
    String wechatOpenId;
    String wechatUnionId;
    String iconUrl;
    String gender;
    String district;
    String agendaBackgroundImageUrl;
    boolean isVip;
    boolean receiveActivityTrailer;
    String imUserId;

    public long getId() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public boolean isReceiveActivityTrailer() {
        return receiveActivityTrailer;
    }

    public void setReceiveActivityTrailer(boolean receiveActivityTrailer) {
        this.receiveActivityTrailer = receiveActivityTrailer;
    }

    public String getImUserId() {
        return imUserId;
    }

    public void setImUserId(String imUserId) {
        this.imUserId = imUserId;
    }
}
