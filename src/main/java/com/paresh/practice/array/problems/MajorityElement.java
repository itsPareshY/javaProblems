package com.paresh.practice.array.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Boyer-Moore Voting Algorithm:
 * The Boyer-Moore Voting Algorithm is an elegant algorithm designed to find the majority element in an array. It works based on the idea that, if you maintain a candidate for the majority element and count how many times it can "win" against other elements, you can identify the majority element.
 *
 * Steps:
 * Initialization:
 *
 * Set a candidate variable to store the current potential majority element.
 * Set a count variable to track the count of the current candidate.
 * First Pass (Find the Candidate):
 *
 * Iterate through the array. For each element:
 * If the count is 0, set the current element as the new candidate and reset the count to 1.
 * If the current element is the same as the candidate, increment count.
 * If the current element is different from the candidate, decrement count.
 * Second Pass (Optional):
 *
 * The first pass gives us a candidate. Since the problem guarantees that the majority element exists, we don't need to explicitly check the count again.
 * However, in some cases (if the problem did not guarantee a majority element), we would need a second pass to verify that the candidate actually appears more than ⌊n / 2⌋ times.
 */
public class MajorityElement {
    public static int majorityElementSpaceNotOptimized(int[] nums) {
        if(nums.length == 1 ){
            return nums[0];
        }
        Map<Integer,Integer> countElement = new HashMap<>();
        for (int i = 0 ; i < nums.length ; i++){
            if(countElement.containsKey(nums[i])){
                countElement.put(nums[i], countElement.get(nums[i])+1);
                if(countElement.get(nums[i]) > (nums.length/2)){
                    return nums[i];
                }
            }
            else{
                countElement.put(nums[i],1);
            }
        }
        return 0;
    }

    public static int majorityElementSpaceOptimized(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for (int i = 1 ; i < nums.length ; i++){
            if(count == 0 ) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            }
            else{
                count--;
            }
        }
        return candidate;
    }


    public static void main (String ... args){
        int[] nums = {0,0,1,1,1,1,1,3,3};
        System.out.println(majorityElementSpaceOptimized(nums));
    }
}
