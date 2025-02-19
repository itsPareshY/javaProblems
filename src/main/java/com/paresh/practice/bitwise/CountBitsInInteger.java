package com.paresh.practice.bitwise;

public class CountBitsInInteger {

    public short countBits(int n){
        short count = 0;
        while(n>0){
            System.out.println("Binary :"+Integer.toBinaryString(n));
            // least significant bit is 1 then increment count
            count += n & 1;
            //shift right by 1
            n >>= 1;
        }
        return count;
    }

    public static void main(String ... args){
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        System.out.println(countBitsInInteger.countBits(7));
    }
}
