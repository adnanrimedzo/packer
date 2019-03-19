package com.mobiquityinc.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pack {
    private List<Item> itemList;
    private double weight;
    private double cost;

    public Pack(List<Item> itemList, double weight, double cost) {
        this.itemList = itemList;
        this.weight = weight;
        this.cost = cost;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        if (itemList.isEmpty()) {
            return "-";
        }

        itemList.sort(Comparator.comparingInt(Item::getIndex));
        return itemList.stream().map(it -> Integer.toString(it.getIndex())).collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pack pack = (Pack) o;
        return Double.compare(pack.weight, weight) == 0 &&
                Double.compare(pack.cost, cost) == 0 &&
                itemList.equals(this.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemList, weight, cost);
    }
}
