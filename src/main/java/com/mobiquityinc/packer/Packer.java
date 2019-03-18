package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Pack;
import com.mobiquityinc.model.PackSpecs;
import com.mobiquityinc.util.PackReader;

import java.util.*;
import java.util.stream.Collectors;

public class Packer {

    private static String NEW_LINE = "\n";

    public static void main(String [] arg) throws APIException {
        System.out.println(pack("/Users/adnan.ozdemir/IdeaProjects/packer/src/test/resources/item_list.txt"));
    }

    public static String pack(String filePath) throws APIException {

        List<PackSpecs> packSpecsList = PackReader.readPackages(filePath);
        Set<Double> calculated = new HashSet<>();

        return packSpecsList.stream().map(p -> knapsackUnbounded(p.getWeightLimit(), p.getItemList(), calculated).toString()).collect(Collectors.joining(NEW_LINE));
    }

    private static Pack knapsackUnbounded(double weightLimit, List<Item> itemList, Set<Double> calculated) {

        double maxCost = Double.MIN_VALUE;
        double minWeight = Double.MAX_VALUE;
        List<Item> stack = new LinkedList<>();

        for (Item item : itemList) {
            if (item.getWeight() <= weightLimit) {

                Pack result = knapsackUnbounded(weightLimit - item.getWeight(), itemList, calculated);
                Double cost = result.getCost() + item.getCost();
                Double weight = result.getWeight() + item.getWeight();
                List<Item> itemStack = result.getItemList();

                if (!itemStack.contains(item) && Double.compare(maxCost, cost) == -1) {
                    maxCost = cost;
                    minWeight = weight;
                    stack = itemStack;
                    stack.add(item);
                    calculated.add(weightLimit);

                }
                else if(!itemStack.contains(item) && Double.compare(maxCost, cost) == 0 && Double.compare(minWeight, weight) == -1){
                    maxCost = cost;
                    minWeight = weight;
                    stack = itemStack;
                    stack.add(item);
                    calculated.add(weightLimit);
                }
            }
        }

        return new Pack(stack, minWeight, maxCost);
    }

}