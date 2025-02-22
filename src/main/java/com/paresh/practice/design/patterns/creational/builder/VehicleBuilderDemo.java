package com.paresh.practice.design.patterns.creational.builder;

public class VehicleBuilderDemo {
    public static void main(String[] args) {
        // Building a Car with engine type and color
        Vehicle car = new Car.CarBuilder("V8 Engine")
                .color("Red")
                .build();
        System.out.println("Vehicle Type: " + car.getType());
        System.out.println("Engine: " + car.getEngine());
        System.out.println("Color: " + car.getColor());

        // Building a Bike with only engine type
        Vehicle bike = new Bike.BikeBuilder("250cc Engine")
                .color("Black")  // Optional
                .build();
        System.out.println("\nVehicle Type: " + bike.getType());
        System.out.println("Engine: " + bike.getEngine());
        System.out.println("Color: " + bike.getColor());
    }
}

// Vehicle interface with common methods for all vehicle types
interface Vehicle {
    //implicit attributes of a vehicle
    String getType();
    //mandatory attributes of a vehicle
    String getEngine();
    //optional attributes of a vehicle
    String getColor();
}

interface VehicleBuilder {
    VehicleBuilder color(String color);
    Vehicle build();
}

// Car implementation of the Vehicle interface
class Car implements Vehicle {
    private final String engine;
    private final String color;

    // Private constructor (used by builder)
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.color = builder.color;
    }

    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public String getEngine() {
        return engine;
    }

    @Override
    public String getColor() {
        return color;
    }

    // Builder class for creating a Car instance
    public static class CarBuilder implements VehicleBuilder {
        private final String engine;
        private String color;

        // Required fields
        public CarBuilder(String engine) {
            this.engine = engine;
        }

        // Optional field
        public CarBuilder color(String color) {
            this.color = color;
            return this;
        }

        public Vehicle build() {
            return new Car(this);
        }
    }
}

// Bike implementation of the Vehicle interface
class Bike implements Vehicle {
    private final String engine;
    private final String color;

    // Private constructor (used by builder)
    private Bike(BikeBuilder builder) {
        this.engine = builder.engine;
        this.color = builder.color;
    }

    @Override
    public String getType() {
        return "Bike";
    }

    @Override
    public String getEngine() {
        return engine;
    }

    @Override
    public String getColor() {
        return color;
    }

    // Builder class for creating a Bike instance
    public static class BikeBuilder {
        private final String engine;
        private String color;

        // Required fields
        public BikeBuilder(String engine) {
            this.engine = engine;
        }

        // Optional field
        public BikeBuilder color(String color) {
            this.color = color;
            return this;
        }

        public Vehicle build() {
            return new Bike(this);
        }
    }
}


