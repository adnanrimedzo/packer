package com.mobiquityinc.model;

import java.util.List;

public class Pack {
    private List<Item> itemList;
    private double maxWeight;

    public Pack(List<Item> itemList, double maxWeight){
        this.itemList = itemList;
        this.maxWeight = maxWeight;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
