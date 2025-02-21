package com.paresh.practice.bitwise;

/**
 * Checking if a number is even or odd — Simple and efficient check using &.
 * Swapping variables without extra memory — Using XOR for swapping values.
 * Setting, unsetting, and checking bits — Handling individual flags or permissions.
 * Efficient multiplication/division by powers of 2 — Using shifts instead of arithmetic operations.
 */
public class BitwiseDay2DayUse {

    public static void main(String[] args) {
        // Checking if a number is even or odd
        int num = 5;
        if (isEven(num)) {
            System.out.println(num + " is even."); //even if last bit is 0
        } else {
            System.out.println(num + " is odd."); //odd if last bit is 1
        }

        swapNumbers();
        bitwiseMultiplicationDivision();
    }

    private static boolean isEven(int num) {
        return (num & 1) == 0;
    }

    private static void swapNumbers() {
        // Swapping two variables without extra memory
        int a = 10;
        int b = 20;
        System.out.println("Before swapping: a = " + a + ", b = " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After swapping: a = " + a + ", b = " + b);
    }


        public static void bitwiseMultiplicationDivision() {
            int number = 8;

            // Multiplication by 2 using left shift
            int multipliedBy2 = number << 1;
            System.out.println(number + " multiplied by 2 is " + multipliedBy2);

            // Division by 2 using right shift
            int dividedBy2 = number >> 1;
            System.out.println(number + " divided by 2 is " + dividedBy2);
        }


}
