package com.paresh.practice.array.problems;

public class JumpGame {

    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for(int i = 0 ; i< nums.length ; i ++){
            if(i > maxReach){
                return false;
            }
            maxReach = Math.max(maxReach,i+nums[i]);
            if(maxReach >= nums.length -1 )
            {
                return  true;
            }
        }
        return false;
    }

    public static void main (String args[]){
        int[] nums = {3,2,1,0,3};

        System.out.println(new JumpGame().canJump(nums));
    }
}
