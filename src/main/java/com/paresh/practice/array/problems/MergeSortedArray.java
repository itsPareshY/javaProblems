package com.paresh.practice.array.problems;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Two-pointer technique: The idea is to use two pointers (or indices), one for each array, and compare the elements at the pointers to decide which element to append to the result. Move the pointer forward after choosing an element.
 *
 * For example:
 *
 * Pointer 1 starts at the end of the first array.
 * Pointer 2 starts at the end of the second array.
 * Compare the elements at the two pointers.
 * Add the smaller element to the result array and move the pointer of the array from which the element was taken.
 * Continue this process until all elements from both arrays are added to the result.

 * Why fill backward?
 *
 * If you're merging into the first array and it has enough space at the end to accommodate the merged elements, you can fill the array from the back (right to left). This ensures that you do not overwrite elements that are still yet to be considered for merging.
 *
 * Edge Cases
 * Empty Arrays: One or both input arrays could be empty. In such cases, you just return the other array as the merged result.
 * Arrays of Different Lengths: The arrays might have different lengths. After you've processed all elements from one array, you'll need to append the remaining elements from the other array.
 * Duplicates: There could be duplicate elements in the arrays, but you don't need to handle them any differently because the arrays are already sorted.
 * Negative Numbers: The arrays may contain negative numbers. The logic for merging doesn’t change; it works regardless of whether the numbers are positive or negative, since sorting already ensures correct order.
 *
 * Time Complexity Considerations
 * The time complexity of merging two sorted arrays is O(n + m), where n and m are the sizes of the two arrays. This is because you iterate through each array once, comparing and merging elements. No extra sorting is needed since the input arrays are already sorted.
 *
 * If you have to do the merging in-place, the time complexity remains O(n + m), but the space complexity becomes O(1) (excluding input arrays), as you’re modifying one of the arrays in place.
 *
 * Optimized Space Usage
 * If you're allowed to use extra space for the result array, then you can simply create a new array large enough to hold all the elements and return that. However, if the task requires in-place merging, the focus shifts to minimizing additional space.

 */

public class MergeSortedArray {

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input arr1");
        String arr1 = scanner.nextLine();
        String[] inputStrArr = arr1.split(" ");
        int[] nums1 = getIntArr(inputStrArr); // 1 2 3 0 0 0
        System.out.println("Input arr1 size");
        int m = scanner.nextInt(); //3
        scanner.nextLine(); // clean the line as next int does not consume line so causing issue reading next arr2
        System.out.println("Input arr2");
        String arr2 = scanner.nextLine();
        inputStrArr = arr2.split(" ");
        int[] nums2 = getIntArr(inputStrArr); // 2 5 6
        System.out.println("Input arr2 size");
        int n = scanner.nextInt(); //3

        int i = m-1; // pointer to last element of nums1
        int j = n-1; // pointer to last element of nums2
        int k = nums1.length -1; // // pointer to last element of nums1 full array

        while(i >=0 && j >=0){
            System.out.println(Arrays.toString(nums1));
                if(nums1[i]> nums2[j]) {
                    nums1[k--] = nums1[i--];
                }else{
                    nums1[k--] = nums2[j--];
                }
        }

        while (j >= 0) {
            System.out.println(Arrays.toString(nums1));
            nums1[k--] = nums2[j--];
        }

        System.out.println(Arrays.toString(nums1));
    }

    private static int[] getIntArr(String[] inputStrArr) {
        int[] array = new int[inputStrArr.length];

        for (int i = 0; i < inputStrArr.length; i++) {
            array[i] = Integer.parseInt(inputStrArr[i]);
        }
        return array;
    }

}
