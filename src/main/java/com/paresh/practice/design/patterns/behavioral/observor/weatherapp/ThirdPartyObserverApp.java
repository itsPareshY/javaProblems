package com.paresh.practice.design.patterns.behavioral.observor.weatherapp;

public class ThirdPartyObserverApp implements Observer, DisplayElement {
    private int temperature;
    private int humidity;
    private int pressure;
    private Subject weatherData;

    public ThirdPartyObserverApp(Subject weatherData) {
        this.weatherData = weatherData;
        System.out.println("ThirdPartyObserverApp registered with WeatherData");
        weatherData.registerObserver(this);
    }


    @Override
    public void display() {
        System.out.println("ThirdPartyObserverApp: Current conditions: " + temperature
                + "F degrees and " + humidity
                + "% humidity and " + pressure + " pressure");
    }

    @Override
    public void update(int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.display();
    }
}
