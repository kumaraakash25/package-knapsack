package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

/**
 * A class for containing the metadata of the package, its size and the items that have to be chosen from
 */
@Getter
@AllArgsConstructor
public class PackageMetaData {
    private Integer packageCapacity;
    private List<Item> items;
}
