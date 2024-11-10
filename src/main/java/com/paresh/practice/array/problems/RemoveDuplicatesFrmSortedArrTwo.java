package com.paresh.practice.array.problems;

import java.util.Arrays;

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

    public static int removeDuplicates(int[] nums) {
        int uniquePos = 2;

        for(int j = 2;j<nums.length;j++){
            if(nums[j] != nums[uniquePos-2]){
                nums[uniquePos] = nums[j];
                uniquePos++;
            }
        }
//        int duplicateCount = 0;
//        int arrLength = 0;
//
//        while(j<nums.length){
//            System.out.println(Arrays.toString(nums)+" "+nums[uniquePos]+" "+nums[j]+" j:"+j+" uniqpos:"+uniquePos);
//            if (nums[uniquePos] == nums[j] && duplicateCount < 2){
//                uniquePos++;
//                j++;
//                duplicateCount+=2;
//                arrLength++;
//            }
//            else if (nums[uniquePos] == nums[j] && duplicateCount == 2 ){
//                j++;
//            } else if (nums[uniquePos] != nums[j] && duplicateCount == 2) {
//                duplicateCount = 0;
//                nums[uniquePos+1] = nums[j];
//                arrLength++;
//                uniquePos=j;
//                j++;
//            }
//            else{
//                uniquePos++;
//                j++;
//                arrLength++;
//            }
//        }
        System.out.println(Arrays.toString(nums));
        return uniquePos;
    }

    public static void main (String ... args){
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
    }
}
