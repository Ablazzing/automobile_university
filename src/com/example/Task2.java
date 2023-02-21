package com.example;

import java.util.*;

import static javax.swing.UIManager.get;

public class Task2 {
    public static void main(String[] args) {
        Map<Integer, Map<String, String[]>> data = GeneratorData.getData();
        System.out.println(getInputCarFromRegion(data));
        getSpecialNumber(data);
        System.out.println(get(data));
    }

    public static Map<Integer, String> getInputCarFromRegion(Map<Integer, Map<String, String[]>> data) {
        Map<Integer, String> inputCar = new HashMap<>();
        data.forEach((region, statistics) -> {
            String[] inputs = statistics.get("input");
            long count = Arrays.stream(inputs)
                    .map(e -> e.substring(6, e.length()))
                    .filter(e -> Integer.parseInt(e) == region)
                    .count();
            inputCar.put(region, "Вернулось " + count + " машин");
        });
        return inputCar;
        // Подсказка, используйте StreamAPI: методы .forEach .map .filter .count
        // Для создания структуры "Номер региона: Колличество вернувшихся машин", можно использовать HashMap
    }

    public static void getSpecialNumber(Map<Integer, Map<String, String[]>> data) {
        data.forEach((region, statistics) -> {
            statistics
                    .entrySet()
                    .stream()
                    .flatMap(e -> Arrays.stream(e.getValue()))
                    .filter(e -> e.matches("М\\d*АВ\\d*"))
                    .distinct()
                    .forEach(System.out::println);
        });
        // Подсказка, используйте StreamAPI: методы  .flatMap .filter .distinct .entrySet
        // Используйте регулярные выражения для поиска номеров по заданному шаблону
        // Ничего возврашать не надо для вывода в консоль используйте .forEach
    }

    public static boolean isThereNumber(Map<Integer, Map<String, String[]>> data) {
        boolean input = data.values().stream().map((e) -> e.get("input"))
                .anyMatch(e -> Arrays.stream(e).anyMatch(y -> y.matches("К473СЕ178")));
        boolean output = data.values().stream().map((e) -> e.get("out"))
                .anyMatch(e -> Arrays.stream(e).anyMatch(y -> y.matches("К473СЕ178")));
        return input || output;

        // Подсказка, используйте StreamAPI: методы  .map .anyMatch
        // Используйте регулярные выражения для поиска номеров по заданному шаблону
    }
}
