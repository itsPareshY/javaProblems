package com.paresh.practice.java8.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostFrequentElement {
    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(20);
        list.add(20);
        list.add(20);
        list.add(25);
        list.add(25);
        list.add(50);
        list.add(50);
        list.add(50);
        list.add(50);

        list.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) //TODO: Important how we are comparing e2.getValue() with e1.getValue()
                .limit(5)
                .forEach(entry -> System.out.print(entry.getKey() + " "));

        //output: [50, 20, 25, 2, 5]
        System.out.println();
        list.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(e ->  ((Map.Entry<Integer, Long>)e).getValue()).reversed()) //TODO: Another way to sort Descending
                .limit(5)
                .forEach(entry -> System.out.print(entry.getKey() + " "));

        System.out.println();
        list.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue)) //TODO: Another way to sort Ascending
                .limit(5)
                .forEach(entry -> System.out.print(entry.getKey() + " "));
    }
}
