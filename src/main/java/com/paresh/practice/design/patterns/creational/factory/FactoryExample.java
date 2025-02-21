package com.paresh.practice.design.patterns.creational.factory;

public class FactoryExample {

    private final ShapeFactory circleFactory ;
    private final ShapeFactory rectangleFactory ;

    FactoryExample(ShapeFactory circleFactory, ShapeFactory rectangleFactory){
        this.circleFactory = circleFactory;
        this.rectangleFactory = rectangleFactory;
    }

    public void createShapes() {
        // Create a Circle using the factory
        Shape circle = circleFactory.createShape();
        circle.draw();  // Output: Drawing a Circle

        // Create a Rectangle using the factory
        Shape rectangle = rectangleFactory.createShape();
        rectangle.draw();  // Output: Drawing a Rectangle
    }

    public static void main(String[] args) {
        FactoryExample factoryExample = new FactoryExample(new CircleFactory(), new RectangleFactory());
        factoryExample.createShapes();
    }

}


interface Shape {
    void draw();
}


class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}


abstract class ShapeFactory {
    // Factory Method
    abstract Shape createShape();
}


class CircleFactory extends ShapeFactory {
    @Override
    Shape createShape() {
        return new Circle();  // Return a Circle object
    }
}

class RectangleFactory extends ShapeFactory {
    @Override
    Shape createShape() {
        return new Rectangle();  // Return a Rectangle object
    }
}

