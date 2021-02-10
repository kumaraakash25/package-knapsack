package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * A class for holding the list of items selected to be put in the package and the value
 */
@Getter
@AllArgsConstructor
public class Result {
    public List<Item> items;
    public double value;
}
