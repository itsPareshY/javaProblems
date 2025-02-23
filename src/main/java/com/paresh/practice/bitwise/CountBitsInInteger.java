package com.paresh.practice.bitwise;

public class CountBitsInInteger {

    public short countBits(int n){
        short count = 0;
        while(n>0){
            // least significant bit is 1 then increment count
            // this count is used to count number of 1's in binary representation of n
            count += n & 1;
            //shift right by 1
            n >>= 1;
        }
        return count;
    }

    public void printBinary(int n){
        System.out.print("Binary Representation of "+n+" is : ");
        while(n>0){
            System.out.print(n & 1);
            n >>= 1;
        }
        System.out.println();
    }

    public static void main(String ... args){
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        int n = 7;
        countBitsInInteger.printBinary(n);
        System.out.println("Count of 1 in Binary representation of "+ n +" is : "+countBitsInInteger.countBits(n));
    }
}
