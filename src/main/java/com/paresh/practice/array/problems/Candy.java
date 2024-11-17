package com.paresh.practice.array.problems;

import java.util.Arrays;

public class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;

        int[] left = new int[n];
        int[] right = new int[n];
       for (int i = 0; i < n; i++){
           left[i] = 1;
           right[i] = 1;
       }

        for(int i = 1; i < n; i++){
            if(ratings[i] > ratings[i-1]){
                left[i] = left[i - 1] + 1;
            }
        }

        for(int i = n - 2; i >= 0 ; i--){
            if(ratings[i] > ratings[i+1]){
                right[i] = right[i + 1] + 1;

            }
        }

        int totalCandies = 0;
        for (int i = 0; i < n; i++) {
            totalCandies += Math.max(left[i], right[i]);
        }

        return totalCandies;
    }

    public static void main (String ... args){
        int[] nums = {1,0,2};
        int[] nums2 = {1,2,2};
        int[] nums3 = {1,3,4,5,1,2,2}; //14
        int[] nums4 = {1,2,87,87,87,2,1}; // 13
                       //1,2,3,1,3,2,1
                       //1,1,1,1,1,1,1
                       //1,1,1,1,2,2,1
                       //1,2,2,1,2,2,1

        Candy candy = new Candy();
        System.out.println(candy.candy(nums));
        System.out.println(candy.candy(nums2));
        System.out.println(candy.candy(nums3));
        System.out.println(candy.candy(nums4));
    }
}
