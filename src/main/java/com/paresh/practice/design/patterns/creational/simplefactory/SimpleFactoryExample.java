package com.paresh.practice.design.patterns.creational.simplefactory;

/**
 * @author https://www.linkedin.com/in/yadavparesh/
 */
// Our Client class
public class SimpleFactoryExample {

    //in client code, we can use the factory to create objects
    ShapeFactory shapeFactory ;

    // Inject the factory into the client
    SimpleFactoryExample(ShapeFactory shapeFactory){
        this.shapeFactory = shapeFactory;
    }

    // Create shapes using the factory
    public void createShapes() {
        // Create a Circle using the Simple Factory
        Shape circle = shapeFactory.createShape("Circle");
        circle.draw();  // Output: Drawing a Circle

        // Create a Rectangle using the Simple Factory
        Shape rectangle = shapeFactory.createShape("Rectangle");
        rectangle.draw();  // Output: Drawing a Rectangle
    }

    public static void main(String[] args) {
        SimpleFactoryExample simpleFactoryExample = new SimpleFactoryExample(new ShapeFactory());
        simpleFactoryExample.createShapes();
    }
}

// Our Product interface
interface Shape {
    void draw();
}

// Concrete Product Circle
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

// Concrete Product Rectangle
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

// Our Factory class
class ShapeFactory {

    //Factory method to create shapes
    public Shape createShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("Circle")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("Rectangle")) {
            return new Rectangle();
        } else {
            throw new IllegalArgumentException("Unknown shape type");
        }
    }
}

