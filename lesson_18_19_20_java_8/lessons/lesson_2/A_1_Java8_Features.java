package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class A_1_Java8_Features {
    public static void main(String[] args) {
        //demoWorkWithList();
        demoMap();

    }

    private static void demoMap() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        map.forEach((k,v)-> System.out.println(k + " " + v));
        map.putIfAbsent("4", "four");
        map.putIfAbsent("4", "fourNew ");
        map.computeIfPresent("5", new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return null;
            }
        });

        System.out.println("---------------");
        map.forEach((k,v)-> System.out.println(k + " " + v));
    }

    private static void demoWorkWithList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        list.forEach(s -> System.out.println("Hello " + s));
        list.removeIf(s -> s.length() == 3);
        System.out.println();
    }


}
