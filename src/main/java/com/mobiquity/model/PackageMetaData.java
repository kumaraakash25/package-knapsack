package com.mobiquity.model;

import java.util.List;


/**
 * A class for containing the metadata of the package, its size and the items that have to be chosen from
 */
public class PackageMetaData {
    private Integer packageCapacity;
    private List<Item> items;

    public PackageMetaData(Integer packageCapacity, List<Item> items) {
        this.packageCapacity = packageCapacity;
        this.items = items;
    }

    public Integer getPackageCapacity() {
        return packageCapacity;
    }

    public List<Item> getItems() {
        return items;
    }
}
