package com.paresh.practice.algorithm.sorting;

public class PracticeMergeSort {

    //Dive and conquer algorithm
    //Divide the array into two halves and recursively sort them
    //Merge the sorted halves
    // copy by reference allows to modify the original array while recursion keeps the sub arrays on the stack
    public static void mergeSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;
        int[] left  = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);

    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }


    public static void main(String... args) {

        int[] arr = {13, 45, 23, 78, 1, 456, 90, 32};
        mergeSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
