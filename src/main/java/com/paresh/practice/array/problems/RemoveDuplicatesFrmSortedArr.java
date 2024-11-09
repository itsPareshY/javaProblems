package com.paresh.practice.array.problems;

import java.util.Arrays;

public class RemoveDuplicatesFrmSortedArr {
    // [0,0,1,1,1,2,2,3,3,4] -> [0,1,2,3,4,0,0,0,0,0]
    public static void main (String args[]){
        int[]  arr = {0,0,1,1,1,2,2,3,3,4};

        int uniquePos1 = 0;
        int uniquePos2 = 0;
        int j = 1;
        int k = arr.length - 1;

        while(j <= k){
            System.out.println(Arrays.toString(arr)+" "+arr[uniquePos1]+" "+arr[j]+" j:"+j+" uniqpos:"+uniquePos1);
            if(arr[uniquePos1] == arr[j]){
                j++;
            }
            else {
                arr[uniquePos1 +1] = arr[j];
                uniquePos1++;
            }
        }
    }
}
