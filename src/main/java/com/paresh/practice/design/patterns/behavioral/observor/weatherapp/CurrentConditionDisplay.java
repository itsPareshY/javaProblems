package com.paresh.practice.design.patterns.behavioral.observor.weatherapp;

public class CurrentConditionDisplay implements Observer, DisplayElement {
    private int temperature;
    private int humidity;
    private int pressure;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        System.out.println("CurrentConditionDisplay registered with WeatherData");
        weatherData.registerObserver(this);
    }

    @Override
    public void update(int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity
                + "% humidity and " + pressure + " pressure");
    }
}
