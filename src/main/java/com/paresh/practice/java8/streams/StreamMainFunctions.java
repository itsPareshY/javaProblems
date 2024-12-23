package com.paresh.practice.java8.streams;

public class StreamMainFunctions
{
    /**
     * Create a Stream:
     *
     * Use Stream.of() for a fixed set of elements.
     * Use list.stream() for a list.
     * Use Arrays.stream(array) for an array.
     * Intermediate Operations (Optional):
     *
     * Filter: filter(predicate) - Filters elements based on a condition.
     * Map: map(mapper) - Transforms each element to a new value.
     * Sorted: sorted() or sorted(Comparator) - Sorts elements.
     * Distinct: distinct() - Removes duplicates.
     * Limit: limit(n) - Limits the stream to the first n elements.
     * Skip: skip(n) - Skips the first n elements.
     * Terminal Operations:
     *
     * forEach: forEach(consumer) - Performs an action on each element.
     * collect: collect(Collectors.toList()), collect(Collectors.toSet()), collect(Collectors.toMap()) - Collects elements into a collection.
     * reduce: reduce(identity, accumulator) - Reduces elements to a single value.
     * min: min(Comparator) - Finds the minimum element.
     * max: max(Comparator) - Finds the maximum element.
     * count: count() - Counts the number of elements.
     * anyMatch: anyMatch(predicate) - Checks if any element matches a condition.
     * allMatch: allMatch(predicate) - Checks if all elements match a condition.
     * findFirst: findFirst() - Finds the first element.
     * findAny: findAny() - Finds any element.
     */

    //TODO Practice below
    /**
     *
     * how to get stream ??
     *    ->  Stream.of("sd","sdsd");
     *    ->  list.stream();
     *      int[] arr = {1,2,3};
     *    ->     Arrays.stream(arr);
     * Collectors
     * COmparator
     * .collect() returns collection
     * Collectors.goupingBy(classifier,collector)
     *
     * skip - skip first n element
     * limit - limit to n emements
     * distinct
     *
     *
     *
     *What is the difference between map and flatMap?

     flatMap: Used for transforming each element into a Stream and then flattening the result into a single Stream.

     map: Transforms each element of the stream into another object. The output is a Stream of the same size

     map: Transforms each element of the stream into another object. The output is a Stream of the same size
     map: Transforms each element of the stream into another object. The output is a Stream of the same size

     */

}
