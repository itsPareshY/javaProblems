package com.paresh.practice.bitwise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountBitsInIntegerTest  {

    @Test
    void countBits_withPositiveNumber() {
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        assertEquals(3, countBitsInInteger.countBits(7));
    }

    @Test
    void countBits_withZero() {
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        assertEquals(0, countBitsInInteger.countBits(0));
    }

    @Test
    void countBits_withNegativeNumber() {
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        assertEquals(0, countBitsInInteger.countBits(-1));
    }

    @Test
    void printBinary_withPositiveNumber() {
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        countBitsInInteger.printBinary(7);
    }

    @Test
    void printBinary_withZero() {
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        countBitsInInteger.printBinary(0);
    }

    @Test
    void printBinary_withNegativeNumber() {
        CountBitsInInteger countBitsInInteger = new CountBitsInInteger();
        countBitsInInteger.printBinary(-1);
    }
}