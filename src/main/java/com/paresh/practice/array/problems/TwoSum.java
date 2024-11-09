package com.paresh.practice.array.problems;


import java.util.HashMap;
import java.util.Map;

/**
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSum {

    public static void main(String args[]){
        int[] nums = {11,7,15,2};
        int target = 9;
        Map<Integer, Integer> indicies = new HashMap<>();


        for (int i=0 ; i < nums.length  ; i++){
            int remainder = target - nums[i];
            if(indicies.containsKey(remainder)){
               // return new int[] { indicies.get(remainder), i };
                System.out.println("{"+indicies.get(remainder)+","+i+"}");
            }
            else{
                indicies.put(nums[i],i);
            }
        }
        //return  new int[] {};
    }
}
