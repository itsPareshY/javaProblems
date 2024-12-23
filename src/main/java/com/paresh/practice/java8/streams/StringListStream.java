package com.paresh.practice.java8.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringListStream {

    public static void main (String ... args){
        List<String> strings = Arrays.asList("Red", "brown", "fox", "jumped", "over", "the", "fence", ".");

        //2. Join strings from a list, separated by commas:

        String joined = String.valueOf(strings.stream().reduce((s1,s2)->s1+","+s2));

        String joined2  = strings.stream().collect(Collectors.joining(","));

        String joined3 = String.join(",",strings);

        System.out.println(joined+"\n"+joined2+"\n"+joined3);

        //3. Group strings based on their length:
        Map<Integer,List<String>> stringGroupByLength = strings.stream()
                .collect(Collectors.groupingBy(String::length));
        stringGroupByLength.forEach((key, value) -> System.out.println(key + " "
                + value));

        //4. Map strings to their lengths:
        Map<String, Integer> stringLengths = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        stringLengths.forEach((key,val)-> System.out.println(key + " "
                + val));

        //5. Merge a list of lists into a single list:
        List<String> newStringList = Arrays.asList("And"," I"," saw"," it"," happen"," .");
        List<List<String>>  listOfLists = new ArrayList<>();
        listOfLists.add(strings);
        listOfLists.add(newStringList);
        List<String> merged = listOfLists.stream().flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println("Merged : "+merged);

        //7. Print elements after skipping the first five:
        strings.stream().skip(5).forEach(System.out::print);

        System.out.println();
       // 8. Collect unique words from sentences:
        String sentence = "Hello there , are you going to the town . I heard there is a fair in the town .";
        Arrays.stream(sentence.split("\\s")).collect(Collectors.toSet())
                .forEach(e -> System.out.print(e + " "));

        //9. Filter out null values from a string list:

        List<String> nonNullStrings = strings.stream()
                                              .filter(Objects::nonNull)
                                              .collect(Collectors.toList());

        //10. Separate integers into even and odd lists:

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Map<Boolean, List<Integer>> evenOddMap = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
//
//Collectors.averagingInt()
//Collectors.counting()
//        Collectors.groupingBy()
//        Collectors.groupingByConcurrent()
//        Collectors.joining()
//        Collectors.mapping()
//        Collectors.maxBy()
//        Collectors.partitioningBy()
//        Collectors.reducing()
//        Collectors.toMap()
//        Collectors.summingInt()
//        Collectors.summarizingInt()

        //11. Sum the squares of integers in a list:

        int sumOfSquares = numbers.stream()
                .map(n -> n * n)
                .reduce(0, Integer::sum);

        int sumOfSquaresDup = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.summingInt(n-> n));

        int sumOfSquaresDup2 = numbers.stream()
                .mapToInt(n -> n * n).sum();
        System.out.println();
        System.out.println(sumOfSquares+" "+sumOfSquaresDup+ " "+sumOfSquaresDup2);

        //12. Convert strings to their lengths:

        List<Integer> stringLengthsN = strings.stream()
                .map(String::length)
                .collect(Collectors.toList());
        stringLengthsN.forEach(System.out::print);
        System.out.println();


        //15. Verify if any string starts with a specific prefix:

        boolean hasPrefix = strings.stream()
                .anyMatch(s -> s.startsWith("prefix"));
       //
        // 16. List the bootom 3 students by rank :

        List<Student> top3Employees = new StreamsPractice().list.stream()
                .sorted(Comparator.comparingInt(Student::getRank).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}
