package com.mobiquity.service;

import com.mobiquity.model.Item;
import com.mobiquity.model.Result;

import java.util.List;

public interface Solver {
    /**
     * Solves the knapsack considering the choice to be made from the
     * list of items so that it remains within capacity
     *
     * @param capacity
     * @param items
     * @return
     */
    Result solve(int capacity, List<Item> items);
}
