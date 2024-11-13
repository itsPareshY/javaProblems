package com.paresh.practice.array.problems;

public class JumpGame {

    public boolean canJump(int[] nums) {
        int sumOfJumps = 0;
        for(int i = 0 ; i< nums.length - 1; i ++){
            if(i > nums.length -3){
                break;
            }
            if(nums[i] == nums.length - 1){
                return true;
            }
            else if(nums[i] < nums.length - 1){
                p
            }

        }
        return false;
    }

    public static void main (String args[]){
        int[] nums = {2,3,1,1,3};

        System.out.println(new JumpGame().canJump(nums));
    }
}
