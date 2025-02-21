package com.paresh.practice.bitwise;

public class SwapBits {


    public static void main(String[] args) {
        long x = 73;
        System.out.println("Original number: " + x);
        System.out.println("Binary representation: " + Long.toBinaryString(x));
        long result = swapBits(x, 1, 6);
        System.out.println("Number after swapping bits: " + result);
        System.out.println("Binary representation: " + Long.toBinaryString(result));
    }

    /**
     * Swaps the bits at positions i and j in the given number x.
     *
     * This method uses bitwise operators to efficiently swap two bits at specified positions without using
     * additional memory or complex logic. The following bitwise operations are leveraged:
     *
     * 1. **Right Shift (>>)**: To extract the individual bits at positions i and j.
     * 2. **Bitwise AND (&)**: To isolate the extracted bits and compare their values.
     * 3. **Left Shift (<<)**: To create a mask that isolates the bits at positions i and j.
     * 4. **Bitwise XOR (^)**: To flip the bits at the given positions when they differ.
     *
     * @param x The number whose bits are to be swapped.
     * @param i The position of the first bit to swap.
     * @param j The position of the second bit to swap.
     * @return The number with the bits at positions i and j swapped.
     *
     * Bit Position	6	5	4	3	2	1	0  Least Significant Bit (LSB) position is 0
     * Binary Value	1	0	0	1	0	0	1
     */
    public static long swapBits(long x, int i, int j) {
        // Step 1: Extract the i-th and j-th bits, and check if they differ.
        // The right shift operator (>>) moves the bit at position i or j to the least significant bit.
        // The AND operator (&) isolates that bit, giving us 1 if the bit is set, or 0 if the bit is clear.
        if (((x >> i) & 1) != ((x >> j) & 1)) {
            // Step 2: If the bits differ, swap them by flipping their values using XOR.
            // The left shift operator (<<) is used to create a bitmask with 1 at the positions i and j.
            // The bitwise OR (|) combines the two shifts, resulting in a bitmask where both bits are set.
            long bitMask = (1L << i) | (1L << j);
            System.out.println("Bit Mask Binary representation: " + Long.toBinaryString(bitMask));

            // Step 3: Use XOR to flip the bits at positions i and j.
            // The XOR (^) operation inverts the bits at positions i and j.
            // If the bits are different, it will swap them. If they are the same, it does nothing.
            x ^= bitMask;
        }

        // Step 4: Return the modified value after swapping the bits.
        return x;
    }

    // >> i Operator to right shift the bits by i positions
    // & 1 Operator to extract the LSB (Least Significant Bit) of the number
    // << i Operator to create a mask with 1 at the i-th position
    // | Operator  to combine the two masks to create a single mask
    // ^ Operator to flip the bits at the i-th and j-th positions

    //create bitwise operator table
    // & Bitwise AND
    // 0 & 0 = 0
    // 0 & 1 = 0
    // 1 & 0 = 0
    // 1 & 1 = 1
    // | Bitwise OR
    // 0 | 0 = 0
    // 0 | 1 = 1
    // 1 | 0 = 1
    // 1 | 1 = 1
    // ^ Bitwise XOR
    // 0 ^ 0 = 0
    // 0 ^ 1 = 1
    // 1 ^ 0 = 1
    // 1 ^ 1 = 0
    // ~ Bitwise NOT
    // ~0 = 1
    // ~1 = 0
    // << Left Shift
    // 1 << 1 = 10
    // 1 << 2 = 100
    // 1 << 3 = 1000
    // >> Right Shift
    // 1000 >> 1 = 100
    // 1000 >> 2 = 10
    // 1000 >> 3 = 1
    // >>> Unsigned Right Shift
    // 1000 >>> 1 = 0100
    // 1000 >>> 2 = 0010
    // 1000 >>> 3 = 0001
    //When to us >>> operator in Java?
    //The >>> operator is used to right shift the bits of a number.
    // It fills the shifted positions with zeros.

}
