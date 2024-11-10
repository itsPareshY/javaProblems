package com.paresh.practice.array.problems;

/**
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 *
 *
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 */
public class RemoveDuplicatesFrmSortedArrTwo {

    public int removeDuplicates(int[] nums) {
        int uniquePos = 0;
        int j = 1;
        int duplicateCount = 0;
        int arrLength = 0;

        while(j<nums.length -1){
            if (nums[uniquePos] == nums[j] && duplicateCount < 2){
                uniquePos++;
                j++;
                duplicateCount+=2;
            }
            else if (duplicateCount == 2 )
        }
        return arrLength;
    }

    public static void main (String ... args){

    }
}
