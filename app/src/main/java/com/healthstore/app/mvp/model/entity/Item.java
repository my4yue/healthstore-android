package com.healthstore.app.mvp.model.entity;

public class Item {

    private String itemId;
    private String name;
    private String spell;
    private String description;
    private Integer type;
    private String minReference;
    private String maxReference;
    private String unit;
    private String selections;
    private Boolean canAddByUser;

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMinReference() {
        return minReference;
    }

    public void setMinReference(String minReference) {
        this.minReference = minReference;
    }

    public String getMaxReference() {
        return maxReference;
    }

    public void setMaxReference(String maxReference) {
        this.maxReference = maxReference;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSelections() {
        return selections;
    }

    public void setSelections(String selections) {
        this.selections = selections;
    }

    public Boolean getCanAddByUser() {
        return canAddByUser;
    }

    public void setCanAddByUser(Boolean canAddByUser) {
        this.canAddByUser = canAddByUser;
    }
}
