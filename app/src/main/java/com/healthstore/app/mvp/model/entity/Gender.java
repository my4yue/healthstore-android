package com.healthstore.app.mvp.model.entity;

import java.util.Arrays;

public enum Gender {

    M("男", 0), F("女", 1);

    private String desc;
    private int order;

    Gender(String desc, int order) {
        this.desc = desc;
        this.order = order;
    }

    public int order() {
        return order;
    }

    public String desc(){
        return desc;
    }

    public static Gender from(String desc){
        return Arrays.stream(Gender.values()).filter(g -> g.desc.equals(desc)).findFirst().orElse(null);
    }

}
