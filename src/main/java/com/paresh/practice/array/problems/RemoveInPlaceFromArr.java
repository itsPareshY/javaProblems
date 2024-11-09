package com.paresh.practice.array.problems;

import java.util.Arrays;

public class RemoveInPlaceFromArr {

    public static void main (String ... args){
        int[] arr = {3,2,2,3};
        int valToRemove = 2;

        int i = 0;
        int j = arr.length - 1;
        int switchCount =0;

        while (i <= j ){
            if(arr[i] == valToRemove && arr[j] != valToRemove){
                int temp = arr[j];
                arr[i++] = temp;
                arr[j--] = -1;
                switchCount++;
            }
            else if(arr[i] == valToRemove && arr[j] == valToRemove){
                arr[j--]=-1;
            }
            else if(arr[i] != valToRemove){
                i++;
                switchCount++;
            }
            System.out.println(Arrays.toString(arr)+" "+switchCount);
        }
        System.out.println("no of elements : "+switchCount);
    }
}
