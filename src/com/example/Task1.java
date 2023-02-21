package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class Task1 {

    public static void main(String[] args) {
        Map<Integer, Map<String, String[]>> data = GeneratorData.getData();
        System.out.println(getFirstFiveCar(data));
        System.out.println(getListAllInputCat(data));
        System.out.println(getStatisticsAllRegion(data));
    }

    public static Map<Integer, List<String>> getFirstFiveCar(Map<Integer, Map<String, String[]>> data) {
        Map<Integer, List<String>> firstFiveNumbersByRegion = new HashMap<>();
        data.forEach((region, regionStatistics) -> {
            String[] numbersOut = regionStatistics.get("out");
            List<String> firstFiveNumbers = Arrays.stream(numbersOut)
                    .limit(5)
                    .collect(Collectors.toList());
            firstFiveNumbersByRegion.put(region, firstFiveNumbers);
        });
        return firstFiveNumbersByRegion;
        //Подсказка, используйте StreamAPI: методы .forEach .limit .collect
        //HashMap : .put
    }

    public static List<String> getListAllInputCat(Map<Integer, Map<String, String[]>> data) {
        List<String> cars = new ArrayList<>();
        data.forEach((region, regionStatistics) -> {
            cars.addAll(Arrays.asList(regionStatistics.get("input")));
        });
        return cars;
        // Подсказка, используйте StreamAPI: методы .forEach;
        // Для создания списка используйте ArrayList методы .add .addAll
    }

    public static Map<Integer, String> getStatisticsAllRegion(Map<Integer, Map<String, String[]>> data) {
        Map<Integer, String> statistics = new TreeMap<>(Collections.reverseOrder());
        data.forEach((region, regionStatistics) -> {
            Integer input = regionStatistics.get("input").length;
            Integer output = regionStatistics.get("out").length;
            statistics.put(region, "Въехало машин " + input + " " + "Выехало машин " + output);
        });
        return statistics;
        //Подсказка, используйте StreamAPI: методы .forEach;
        //Для создание отсортированной реализации Map используйте класс TreeMap
    }
}
