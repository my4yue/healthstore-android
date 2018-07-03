package com.healthstore.app.model;

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
}
