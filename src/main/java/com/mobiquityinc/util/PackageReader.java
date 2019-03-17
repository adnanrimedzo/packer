package com.mobiquityinc.util;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Pack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PackageReader {


    private static String DELIMINATOR_1 = ":";
    private static String DELIMINATOR_2 = ",";
    private static String ITEM_PATTERN = "\\((.*?)\\)";

//    public static void main(String[] args) throws APIException {
//        String fileName = "/Users/adnanrimedzo/IdeaProjects/packer/src/test/resources/item_list.txt";
//        readPackages(fileName);
//    }

    public static List<Pack> readPackages(String fileName) throws APIException {

        List<Pack> packageList = new LinkedList<Pack>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(l -> packageList.add(readPackage(l)));

        } catch (IOException e) {
            throw new APIException("INVALID FILE");
        }

        return packageList;
    }

    private static Pack readPackage(String line) {
        String[] keys = line.split(DELIMINATOR_1);

        Pattern p = Pattern.compile(ITEM_PATTERN);
        Matcher m = p.matcher(line);
        List<Item> itemList = new LinkedList<>();

        double maxWeight = Double.valueOf(keys[0]);
        while (m.find()) {
            itemList.add(readItem(m.group(1)));
        }

        itemList.sort((i1, i2) -> Double.compare(i2.getWeight(), i1.getWeight()));

        return new Pack(itemList, maxWeight);
    }

    private static Item readItem(String line) {
        String[] keys = line.split(DELIMINATOR_2);

        return new Item(Integer.valueOf(keys[0]), Double.valueOf(keys[1]),
                Double.valueOf(keys[2].replaceAll("[$,€]", "")));
    }

}
