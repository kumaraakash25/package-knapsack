package com.mobiquity.model;

/**
 * A class which contains the metadata of each item
 */
public class Item {
    private Integer index;
    private Double weight;
    private Integer value;

    public Item(Integer index, Double weight, Integer value) {
        this.index = index;
        this.weight = weight;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getValue() {
        return value;
    }
}
