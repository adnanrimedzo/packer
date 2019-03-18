package com.mobiquityinc.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PackSpecs {
    private List<Item> itemList;
    private double weightLimit;

    public PackSpecs(List<Item> itemList, double weightLimit){
        this.itemList = itemList;
        this.weightLimit = weightLimit;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackSpecs packSpecs = (PackSpecs) o;
        return Double.compare(packSpecs.weightLimit, weightLimit) == 0 &&
                Objects.equals(itemList, packSpecs.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemList, weightLimit);
    }
}
