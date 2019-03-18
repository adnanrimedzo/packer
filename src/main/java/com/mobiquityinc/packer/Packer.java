package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Pack;
import com.mobiquityinc.model.PackSpecs;
import com.mobiquityinc.util.PackReader;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Inorder to reduce time complexity unbounded knapsack algorithm used.
 * Dynamic Recursive knapsack algorithm alloves to reduce code complexity and time complexity.
 * Simple Client (PackerApplication) added for getting a pattern, like a simple mvc pattern.
 * Fof holding specs of an item, Item model added.
 * For getting items which are selected for packing, PackSpeck model added.
 * For getting result pack, Pack model added.
 * knapsackUnbounded function returns Pack model which contains item list, final weight and final cost
 * */

public class Packer {

    private static String NEW_LINE = "\n";

    public static String pack(String filePath) throws APIException {

        List<PackSpecs> packSpecsList = PackReader.readPackages(filePath);

        return packSpecsList.stream().map(p -> knapsackUnbounded(p.getWeightLimit(),
                p.getItemList()).toString()).collect(Collectors.joining(NEW_LINE));
    }

    public static Pack knapsackUnbounded(double weightLimit, List<Item> itemList) {

        double maxCost = 0.0;
        double minWeight = 0.0;
        List<Item> stack = new LinkedList<>();

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (item.getWeight() <= weightLimit) {

                List<Item> subItemList = itemList.subList(i + 1, itemList.size());

                Pack result = knapsackUnbounded(weightLimit - item.getWeight(), subItemList);
                Double cost = result.getCost() + item.getCost();
                Double weight = result.getWeight() + item.getWeight();
                List<Item> itemStack = result.getItemList();

                if (Double.compare(maxCost, cost) == -1) {
                    maxCost = cost;
                    minWeight = weight;
                    stack = itemStack;
                    stack.add(item);
                }
            }
        }

        return new Pack(stack, minWeight, maxCost);
    }

}