package com.paresh.practice.string.problems;

//Leetcode 1415
//A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
//Given three integers a, b and c, return any string s, which satisfies following conditions:
public class HappyString {
    public String getHappyString(int n, int k) {
        int totalStrings = (int) Math.pow(2, n-1) * 3;
        if(k > totalStrings) return "";
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 3;
        char[] chars = {'a','b','c'};
        while(n > 0){
            int mid = (start + end) / 2;
            if(k <= mid){
                sb.append(chars[0]);
                end = mid;
            } else if(k > mid && k <= 2*mid){
                sb.append(chars[1]);
                start = mid;
                end = 2*mid;
            } else {
                sb.append(chars[2]);
                start = 2*mid;
            }
            n--;
        }
        return sb.toString();
    }

    public static void main(String args[]){
        HappyString happyString = new HappyString();
        System.out.println(happyString.getHappyString(1,3));
        System.out.println(happyString.getHappyString(1,4));
        System.out.println(happyString.getHappyString(3,9));
        System.out.println(happyString.getHappyString(2,7));
    }
}
