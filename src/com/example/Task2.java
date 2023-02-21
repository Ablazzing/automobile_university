package com.example;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {
        //Работаем с номерами:
        //1. Написать метод, который выведет сколько всего машин со спец номерами:
        // начинаются на M и заканчиваются на АВ (Номера должны быть уникальны)
        Map<Integer, Map<String, String[]>> data = GeneratorData.getData();

        //2. Написать метод, который вернет: сколько машин вернулось в свой родной регион.
        //Необходима сумма по всем регионам.
        //Номер региона соответствует коду региона из номера машины в перечне input (въехавшие)
        //Пример:
//           { 1 : {
//                "out" : ["К474СЕ178"],
//                "input": ["А222РТ001" <- СОВПАДЕНИЕ ]
//              },
//             2 : {
//                  "out" : ["К722АВ12", "А222РТ178"],
//                  "input" : ["М001АВ01", "А023РВ73"],
//             }
//           ..
//            }
        System.out.println(countComeBack(data));

        //3. Создать метод, который показывает есть ли номер "К473СЕ178" в данных.
        System.out.println(findNumber(data, "Н560ЕВ894"));
    }

    public static Long countComeBack(Map<Integer, Map<String, String[]>> data) {
        return data.entrySet().stream().map(
                e -> Arrays.stream(e.getValue().get("out"))
                        .filter(car -> Integer.parseInt(car.substring(6)) == e.getKey())
                        .count())
                .reduce(Long::sum)
                .orElse(0L);
    }

    public static boolean findNumber(Map<Integer, Map<String, String[]>> data, String number) {
        return data.entrySet().stream().flatMap(e ->
                        Stream.concat(Arrays.stream(e.getValue().get("out")), Arrays.stream(e.getValue().get("input")))
                )
                .anyMatch(e -> e.equals(number));
    }
}
