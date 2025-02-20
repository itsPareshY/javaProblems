package com.paresh.practice.bitwise;

//LeetCode 338
public class CountBitsArr {

    public int[] countBitsTimeLimitExceeded(int n) {
        int[] arr = new int[n+1];
        for (int i = 0; i<=n ;i++){
            int count = 0;
            while(i>0){
                count += i & 1;
                i >>= 1; // time limit exceeded because of this line as modifying the loop variable to avoid create copy of i
                //  int num = i;  // Create a copy to manipulate within the while loop
            }
            arr[i] = count;
        }
        return arr;
    }

    //TODO to optimize time complexity may need to increase space complexity
    // create byte array of 32  size and store the count of 1s for each number ??

    // Fixed Time Limit Exceeded issue by creating a copy of i and manipulating it within the while loop
    public int[] countBitsFixedTimeLimitErrorofAboveMethod(int n) {
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int count = 0;
            int num = i; // Create a copy to manipulate within the while loop
            while (num > 0) {
                count += num & 1;
                num >>= 1;
            }
            arr[i] = count;
        }
        return arr;
    }

    public int[] countBitsLoop(int num) {
        int[] result = new int[num+1];
        for(int i = 0; i <= num; i++){
            result[i] = countBits(i);
        }
        return result;
    }

    public int countBits(int n){
        short count = 0;
        while(n>0){
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public static void main(String ... args){
        CountBitsArr countBitsArr = new CountBitsArr();
        int[] result = countBitsArr.countBitsLoop(2);
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i]+" ");
        }
    }
}
