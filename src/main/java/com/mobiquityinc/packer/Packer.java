package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Pack;
import com.mobiquityinc.util.PackageReader;
import javafx.util.Pair;

import java.util.List;
import java.util.Stack;

public class Packer {

    public static String pack(String filePath) throws APIException {

        List<Pack> packList = PackageReader.readPackages(filePath);

        //packList.stream().forEach(p-> System.out.println(knapsackUnbounded(p.getMaxWeight(), p)));

        for (int i = 0; i < packList.size(); i++) {
            Pair<Double, Stack<Item>> x = knapsackUnbounded(packList.get(i).getMaxWeight(), packList.get(i));
            System.out.println(x);
        }

        return null;
    }

//    public static void main(String[] args) throws APIException {
//        pack("/Users/adnanrimedzo/IdeaProjects/packer/src/test/resources/item_list.txt");
//    }

    public static Pair<Double, Stack<Item>> knapsackUnbounded(double w, Pack pack) {

        double max = 0.0;
        Stack<Item> stack = new Stack<>();

        for (Item item : pack.getItemList()) {
            if (item.getWeight() <= w) {

                Pair<Double, Stack<Item>> result = knapsackUnbounded(w - item.getWeight(), pack);
                Double cost = result.getKey();
                Stack<Item> itemStack = result.getValue();

                if (!itemStack.contains(item) && max < item.getCost() + cost) {
                    max = item.getCost() + cost;
                    stack = itemStack;
                    stack.add(item);
                }
            }
        }

        return new Pair<>(max, stack);
    }

}