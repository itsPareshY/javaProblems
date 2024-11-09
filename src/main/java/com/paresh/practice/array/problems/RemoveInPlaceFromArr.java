package com.paresh.practice.array.problems;

import java.util.Arrays;

public class RemoveInPlaceFromArr {

    public static void main (String ... args){
        int[] nums = {3,2,2,3};
        int val = 2;

        int i = 0;
        int j = nums.length - 1;
        int switchCount =0;

        while (i <= j ){
            if(nums[i] == val && nums[j] != val){
                int temp = nums[j];
                nums[i++] = temp;
                nums[j--] = -1;
                switchCount++;
            }
            else if(nums[i] == val && nums[j] == val){
                nums[j--]=-1;
            }
            else if(nums[i] != val){
                i++;
                switchCount++;
            }
            System.out.println(Arrays.toString(nums)+" "+switchCount);
        }
        System.out.println("no of elements : "+switchCount);
    }
}
