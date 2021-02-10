package com.mobiquity.service;

import com.mobiquity.model.Item;
import com.mobiquity.model.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PackageKnapsackSolver implements Solver {

    @Override
    public Result solve(int capacity, List<Item> items) {
        Double[] wt = items.stream().map(Item::getWeight).toArray(Double[]::new);
        Integer[] val = items.stream().map(Item::getValue).toArray(Integer[]::new);
        Boolean visited[] = new Boolean[items.size()];
        Arrays.fill(visited, Boolean.FALSE);
        Integer maxValue = maximizeValueFor(capacity, wt, val, items.size(), visited);
        List<Item> solutionItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (visited[i]) {
                solutionItems.add(items.get(i));
            }
        }
        solutionItems.sort(Comparator.comparing(Item::getIndex));
        return new Result(solutionItems, maxValue);
    }

    private static Integer maximizeValueFor(Integer W, Double wt[], Integer val[], Integer N, Boolean visited[]) {
        if (N == 0 || W == 0) {
            return 0;
        }
        if (wt[N - 1] > W) {
            return maximizeValueFor(W, wt, val, N - 1, visited);
        } else {
            Boolean v1[] = new Boolean[visited.length];
            Boolean v2[] = new Boolean[visited.length];
            System.arraycopy(visited, 0, v1, 0, v1.length);
            System.arraycopy(visited, 0, v2, 0, v2.length);
            v1[N - 1] = true;
            Integer s1 = val[N - 1] + maximizeValueFor(W - wt[N - 1].intValue(), wt, val, N - 1, v1);
            Integer s2 = maximizeValueFor(W, wt, val, N - 1, v2);
            if (s1 > s2) {
                System.arraycopy(v1, 0, visited, 0, v1.length);
                return s1;
            } else {
                System.arraycopy(v2, 0, visited, 0, v2.length);
                return s2;
            }
        }
    }
}
