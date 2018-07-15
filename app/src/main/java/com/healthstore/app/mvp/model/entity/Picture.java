package com.healthstore.app.mvp.model.entity;

public class Picture implements Entity{

    private Long id;
    private String name;
    private String picUrl;
    private Long createAt;
    private Long modifyAt;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Long modifyAt) {
        this.modifyAt = modifyAt;
    }
}
