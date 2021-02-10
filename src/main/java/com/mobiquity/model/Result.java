package com.mobiquity.model;

import java.util.List;

/**
 * A class for holding the list of items selected to be put in the package and the value
 */
public class Result {
    public List<Item> items;
    public double value;

    public Result(List<Item> items, double value) {
        this.items = items;
        this.value = value;
    }

    public List<Item> getItems() {
        return items;
    }
}
