package com.healthstore.app.mvp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface Entity {

//    String errorString;
//    Integer errorCode;
//
//    public String getErrorString() {
//        return errorString;
//    }
//
//    public void setErrorString(String errorString) {
//        this.errorString = errorString;
//    }
//
//    public Integer getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(Integer errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    @JsonIgnore
//    public boolean isSucceed() {
//        return errorCode == null || errorCode == 0;
//    }
}
