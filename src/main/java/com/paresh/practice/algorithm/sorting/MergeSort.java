package com.paresh.practice.algorithm.sorting;

        public class MergeSort {

            // Merge sort algorithm
            public static void mergeSort(int[] arr) {
                // Base case: If the array has less than 2 elements, no need to sort
                if (arr == null || arr.length < 2) return;
                // Split the array into two halves
                int mid = arr.length / 2;
                // Create two arrays to hold the elements of the two halves
                int[] left = new int[mid];
                // Create two arrays to hold the elements of the two halves
                int[] right = new int[arr.length - mid];
                //  Copy elements from the original array to the left array
                System.arraycopy(arr, 0, left, 0, mid);
                //  Copy elements from the original array to the right array
                System.arraycopy(arr, mid, right, 0, arr.length - mid);
                // Recursively sort the two halves
                mergeSort(left);
                // Recursively sort the two halves
                mergeSort(right);
                // Merge the sorted halves
                merge(arr, left, right);
            }

            public static void merge(int[] array, int[] left, int[] right) {
                int i = 0, j = 0, k = 0;
                // Copy elements from left and right arrays to the main array
                while (i < left.length && j < right.length) {
                    array[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
                }
                // Copy remaining elements from left array
                while (i < left.length) array[k++] = left[i++];
                // Copy remaining elements from right array
                while (j < right.length) array[k++] = right[j++];
            }

            public static void main(String[] args) {
                int[] arr = {12, 11, 13, 5, 6, 7};
                mergeSort(arr);
                for (int i : arr) System.out.print(i + " ");
            }
        }