package com.healthstore.app.mvp.model.entity;

import java.util.Arrays;

public enum Gender {

    M("男"), F("女");

    private String desc;

    Gender(String desc) {
        this.desc = desc;
    }

    public String desc(){
        return desc;
    }

    public static Gender from(String desc){
        return Arrays.stream(Gender.values()).filter(g -> g.desc.equals(desc)).findFirst().orElse(null);
    }

}
