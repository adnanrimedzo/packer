package com.mobiquityinc.model;

import java.util.Objects;

public class Item {

    private int index;
    private double weight;
    private double cost;

    public Item(int index, double weight, double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public double getWeight() {

        return weight;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return index == item.index &&
                Double.compare(item.weight, weight) == 0 &&
                Double.compare(item.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, weight, cost);
    }

    @Override
    public String toString() {
        return "" + index;
    }
}
