package com.paresh.practice.bitwise;

public class ParityCheck {

    public byte parityCheck(long x){
        byte result = 0;
        while(x != 0){
            result ^= (x & 1); // 7 -> 0111
            x >>= 1;
        }
        return result;
    }

    public static void main(String ... args){
        ParityCheck parityCheck = new ParityCheck();
        byte parityOf7 = parityCheck.parityCheck(7);
        System.out.println(parityOf7); // 7 -> 0111 -> 1(odd)

        byte parityOf5 = parityCheck.parityCheck(5);
        System.out.println(parityOf5); // 5 -> 101 -> 0 (even)
    }
}

/**
 * The `parity` method you've shared is used to determine the **parity** of a given long integer `x`. In computing, **parity** refers to whether the number of 1-bits (set bits) in a binary representation of a number is odd or even. Here's a breakdown of how this method works:
 *
 * ### Method Explanation:
 *
 * ```java
 * public static short parity(long x) {
 *     short result = 0;
 *     while (x != 0) {
 *         result ^= (x & 1);
 *         x >>= 1;
 *     }
 *     return result;
 * }
 * ```
 *
 * 1. **Parameters**:
 *    - The method takes one parameter `x`, which is a `long` type (64-bit signed integer).
 *
 * 2. **Initial Setup**:
 *    - A local variable `result` is initialized to `0`. This will hold the parity result, and it will ultimately be returned.
 *
 * 3. **While Loop**:
 *    - The loop continues to run while `x != 0`. This means the loop will process the bits of `x` until it is reduced to `0`.
 *
 * 4. **XOR operation** (`result ^= (x & 1)`):
 *    - `x & 1` checks the least significant bit (LSB) of `x`. It does this by performing a bitwise AND operation between `x` and `1`.
 *      - If the LSB is `1`, the result will be `1`.
 *      - If the LSB is `0`, the result will be `0`.
 *    - Then, `result ^= (x & 1)` performs a **XOR** operation between the current `result` and the LSB of `x`. This effectively toggles the value of `result` each time a `1` is encountered in `x`.
 *      - XOR works as follows:
 *        - `0 ^ 1 = 1` (if a 1-bit is encountered, toggle the result)
 *        - `1 ^ 1 = 0` (if another 1-bit is encountered, toggle again)
 *        - `0 ^ 0 = 0` (if a 0-bit is encountered, leave the result unchanged)
 *
 * 5. **Right Shift** (`x >>= 1`):
 *    - `x >>= 1` shifts the bits of `x` one position to the right. This essentially drops the LSB that was just processed and prepares `x` for the next iteration. This continues until all bits of `x` have been checked.
 *
 * 6. **Return the Result**:
 *    - Once all the bits of `x` are processed, the final value of `result` is returned.
 *    - If the number of `1`s in the binary representation of `x` is odd, `result` will be `1`. If it's even, `result` will be `0`.
 *
 * ### Example:
 *
 * - **Input**: `x = 5` (binary `101`)
 * - **Steps**:
 *   - Initially, `result = 0`.
 *   - First iteration:
 *     - `x & 1 = 1` (because the LSB is 1)
 *     - `result ^= 1` → `result = 1`
 *     - `x >>= 1` → `x = 2` (binary `10`)
 *   - Second iteration:
 *     - `x & 1 = 0` (because the LSB is 0)
 *     - `result ^= 0` → `result = 1` (no change)
 *     - `x >>= 1` → `x = 1` (binary `1`)
 *   - Third iteration:
 *     - `x & 1 = 1` (because the LSB is 1)
 *     - `result ^= 1` → `result = 0` (toggle)
 *     - `x >>= 1` → `x = 0`
 *   - The loop ends since `x = 0`.
 *   - The final result is `0`, meaning the number of `1`s in `5`'s binary representation (`101`) is even (so the parity is even).
 *
 * ### Summary:
 * - The method calculates the **parity** of the binary representation of a number by toggling a `result` each time a `1` bit is encountered.
 * - If the number of `1` bits is odd, it returns `1`; otherwise, it returns `0`.
 *
 * In simpler terms, this method checks whether the number of `1` bits in `x` is odd or even, and the result represents that.
 */
