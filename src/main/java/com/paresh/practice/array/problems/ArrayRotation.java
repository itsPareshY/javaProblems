package com.paresh.practice.array.problems;

import java.util.Arrays;

public class ArrayRotation {
    public static void main (String ... args){
        int[] nums = {1,2,3,4,5,6,7};
        //nums = new ArrayRotation().myOriginalRotate(nums,3);
        new ArrayRotation().rotateSpaceOptimized(nums,3);
        System.out.println(Arrays.toString(nums));
    }

    public void rotateSpaceOptimized(int[] nums, int k){
    k= k% nums.length;
    reverse(nums,0,nums.length -1);
    reverse(nums,0,k -1);
    reverse(nums,k,nums.length-1);

    }

    public void reverse(int[] nums,int start, int end){
        while(start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end]= temp;
            start++;
            end--;
        }
    }

    public int[] myOriginalRotate(int[] nums, int k){
        int[] output = new int[nums.length];
        for(int i = 0 ; i < nums.length ; i++){
            if(i<k){
                output[i] = nums[nums.length-k+i];
            }
            else {
                output[i] = nums[i-k];
            }
        }
        return output;
    }

}
