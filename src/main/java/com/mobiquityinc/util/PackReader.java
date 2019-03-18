package com.mobiquityinc.util;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.PackSpecs;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PackReader {

    private static String DELIMINATOR_1 = ":";
    private static String DELIMINATOR_2 = ",";
    private static String ITEM_REGEX = "\\((.*?)\\)";
    private static String CURRENCY_REGEX = "[$,€]";

    public static List<PackSpecs> readPackages(String fileName) throws APIException {

        List<PackSpecs> packageList = new LinkedList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(l -> packageList.add(readPackage(l)));
        } catch (Exception e) {
            throw new APIException("INVALID FILE");
        }

        return packageList;
    }

    private static PackSpecs readPackage(String line) {

        String[] keys = line.split(DELIMINATOR_1);

        Pattern p = Pattern.compile(ITEM_REGEX);
        Matcher m = p.matcher(line);
        List<Item> itemList = new LinkedList<>();

        double maxWeight = Double.parseDouble(keys[0]);
        while (m.find()) {
            itemList.add(readItem(m.group(1)));
        }
        itemList.sort(Comparator.comparingDouble(Item::getWeight));

        return new PackSpecs(itemList, maxWeight);
    }

    private static Item readItem(String line) {

        String[] keys = line.split(DELIMINATOR_2);

        return new Item(Integer.valueOf(keys[0]), Double.valueOf(keys[1]),
                Double.valueOf(keys[2].replaceAll(CURRENCY_REGEX, "")));
    }

    private PackReader() {
        throw new IllegalStateException("Utility class");
    }

}
