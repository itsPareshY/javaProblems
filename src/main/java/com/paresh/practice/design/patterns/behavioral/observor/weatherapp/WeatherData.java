package com.paresh.practice.design.patterns.behavioral.observor.weatherapp;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    List<Observer> observers;
    private int temperature;
    private int humidity;
    private int pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        System.out.println("Removing observer: " + observer.getClass().getSimpleName());
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        // Notify all observers in parallel
        observers.parallelStream().forEach(observer->{
            System.out.println("Notifying observer: " + observer.getClass().getSimpleName());
            observer.update(temperature, humidity, pressure);
        });
    }

    public void setMeasurements(int temprature, int humidity, int pressure) {
        System.out.println("Setting measurements in WeatherData");
        this.temperature = temprature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }
}
