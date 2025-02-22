package com.paresh.practice.java.examples;

public class Immutable {

    // Immutable class example: representing a Point (x, y) in 2D space
    // Class is final to prevent inheritance
    public final class Point {

        // Fields are private and final to ensure they cannot
        // be modified after initialization
        private final int x;
        private final int y;

        // Constructor to initialize the fields
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Getter methods return the values (no setters provided)
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        // No Setter methods provided, as the class is immutable

        // Example use case for immutability:
        // The Point class cannot be modified after creation, ensuring the object's state remains consistent.

        // For mutable fields, we would return a defensive copy instead of directly returning the field.
        // This ensures external changes do not affect the state of the immutable object.

    }

}
