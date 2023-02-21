package com.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Task1 {
    public static void main(String[] args) {
//        Входящие данные
//        Map<Integer, Map<String, String[]>> - где ключ первого уровня - номер региона,
//        out, input - ключи второго уровня (выезд/въезд), и String[] - перечень номеров машин въехавших/выехавших.
//           { 1 : {
//                "out" : ["К474СЕ178"],
//                "input": ["А222РТ178"]
//              },
//             2 : {
//                  "out" : ["К722АВ12", "А222РТ178"],
//                  "input" : ["М001АВ01", "А023РВ73"],
//             }
//           ..
//            }
        Map<Integer, Map<String, String[]>> data = GeneratorData.getData();

        //Собираем статистику по каждому региону:
        //1. Написать метод, который выбирает первые 5 (или меньше, если нет 5) выехавших машин из каждого региона.
        //Сохранить в следующей структуре:
        // { 1: [""А222РТ001""]
        //   2: ["К722АВ12", "А222РТ178"]
        //..
        // }
        getFirst5Out(data).forEach((integer, strings) -> System.out.println(integer + Arrays.toString(strings)));
        //2. Создать метод, который возвращает список всех въехавших машин из всех регионов используя Stream api.
        System.out.println(getAllOut(data));

        //3. Составить статистику по каждому региону: сколько машин въехало, сколько выехало.
        //Регионы должны быть отсортированы: от самого большого номера к самому маленькому.
        System.out.println(getRegionStatic(data));
    }


    public static Map<Integer, Map<String, Integer>> getRegionStatic(Map<Integer, Map<String, String[]>> data) {
        TreeMap<Integer, Map<String, Integer>> result = new TreeMap<>(Comparator.reverseOrder());
        data.forEach((key, value) -> result.put(key,
                Map.of("out", value.get("out").length, "input", value.get("input").length)));
        return result;
    }


    public static Map<Integer, String[]> getFirst5Out(Map<Integer, Map<String, String[]>> data) {
        return data.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey,
                        (value) -> Arrays.copyOfRange(value.getValue().get("input"), 0, 5))
        );
    }

    public static List<String> getAllOut(Map<Integer, Map<String, String[]>> data) {
        return data.values().stream().flatMap(e -> Arrays.stream(e.get("out"))).toList();
    }
}
