package com.paresh.practice.array.problems;

public class JumpGameII {

    public int jump(int[] nums){
        int jumpCount = 0;
        if (nums.length == 1) return jumpCount;
        int maxReach = 0;
        int endOfThisJump = 0 ;
        for(int i =0 ; i<nums.length;i++){
            if(i > maxReach){
                return 0;
            }
            maxReach= Math.max(maxReach,i+nums[i]);
            if(i == endOfThisJump){
                if (i != nums.length -1 ){
                    jumpCount++;
                    endOfThisJump = maxReach;
                }
                else {
                    break ;
                }
            }
        }
        return jumpCount;
    }

    public static void main(String ... args){
        int[] jumps1 = {2,3,1,1,4};
        int[] jumps2 = {2,3,0,1,4};
        int[] jumps3 = {1,2,1,1,1};

        JumpGameII obj = new JumpGameII();
        System.out.println(obj.jump(jumps3));
       // System.out.println(obj.jump(jumps2));
    }
}
