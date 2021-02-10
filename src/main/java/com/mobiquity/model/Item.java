package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * A class which contains the metadata of each item
 */
@Builder
@Getter
@AllArgsConstructor
public class Item {
    private Integer index;
    private Double weight;
    private Integer value;
}
