package com.paresh.practice.design.patterns.creational.abstractfactory;

/**
 * @author https://www.linkedin.com/in/yadavparesh/
 */
public class AbstractFactoryExample {

    public static void main(String[] args) {
        // Create a LightThemeFactory
        ShapeFactory lightThemeFactory = new LightThemeFactory();
        Shape lightCircle = lightThemeFactory.createCircle();
        lightCircle.draw();  // Output: Drawing a Circle in Light Theme
        Shape lightRectangle = lightThemeFactory.createRectangle();
        lightRectangle.draw();  // Output: Drawing a Rectangle in Light Theme

        // Create a DarkThemeFactory
        ShapeFactory darkThemeFactory = new DarkThemeFactory();
        Shape darkCircle = darkThemeFactory.createCircle();
        darkCircle.draw();  // Output: Drawing a Circle in Dark Theme
        Shape darkRectangle = darkThemeFactory.createRectangle();
        darkRectangle.draw();  // Output: Drawing a Rectangle in Dark Theme
    }


}

interface Shape {
    void draw();
}

class LightCircle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle in Light Theme");
    }
}

class DarkCircle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle in Dark Theme");
    }
}

class LightRectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle in Light Theme");
    }
}

class DarkRectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle in Dark Theme");
    }
}

//creating all shapes in a family
interface ShapeFactory {
    Shape createCircle();
    Shape createRectangle();
}

// Light Theme Shapes Family
class LightThemeFactory implements ShapeFactory {
    @Override
    public Shape createCircle() {
        return new LightCircle();  // Creates a LightCircle
    }

    @Override
    public Shape createRectangle() {
        return new LightRectangle();  // Creates a LightRectangle
    }
}

// Dark Theme Shapes Family
class DarkThemeFactory implements ShapeFactory {
    @Override
    public Shape createCircle() {
        return new DarkCircle();  // Creates a DarkCircle
    }

    @Override
    public Shape createRectangle() {
        return new DarkRectangle();  // Creates a DarkRectangle
    }
}
