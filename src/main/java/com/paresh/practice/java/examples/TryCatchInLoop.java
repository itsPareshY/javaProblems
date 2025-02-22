package com.paresh.practice.java.examples;

public class TryCatchInLoop {

    public static void main(String[] args) {
        TryCatchInLoop tryCatchInLoop = new TryCatchInLoop();
        tryCatchInLoop.tryCatchInLoop();
        tryCatchInLoop.forLoopInsideTryCatch();

    }

    public void tryCatchInLoop() {
        int[] arr = {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            try {
                throw new Exception("Exception thrown from tryCatchInLoop");
            } catch (Exception e) {
                System.out.println("Exception caught inside tryCatchInLoop for iteration " + arr[i] + " the execution continue after exception thrown");
            }
        }
        System.out.println("Use case: If you want to continue the loop even after exception thrown");
        System.out.println("Example: Validate each element in the array and prepare response list for easier understanding of client which all elements they sent are invalid");
    }

    public void forLoopInsideTryCatch() {
        int[] arr = {1, 2, 3, 4, 5};
        try {
            for (int i = 0; i < arr.length; i++) {
                System.out.println("forLoopInsideTryCatch Iteration " + arr[i]);
                if (i == 1) {
                    throw new Exception("Exception thrown from forLoopInsideTryCatch");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception caught inside forLoopInsideTryCatch the execution break after exception thrown no further iterations");
            System.out.println("Use case: If you want to break the loop after exception thrown\nExample: when you know processing further iterations will not be useful after exception thrown");
        }
    }
}
