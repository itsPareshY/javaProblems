package com.paresh.practice.design.patterns.behavioral.observor.weatherapp;

import java.util.ArrayList;
import java.util.List;

public class WeatherApp {
    public static void main(String ... args){
        WeatherData weatherData = new WeatherData();

        // Registering observers
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        ThirdPartyObserverApp thirdPartyObserverApp = new ThirdPartyObserverApp(weatherData);
        weatherData.setMeasurements(80, 65, 30);
        weatherData.setMeasurements(82, 70, 29);
        weatherData.removeObserver(thirdPartyObserverApp);
        weatherData.setMeasurements(78, 90, 29);

    }
}

interface Observer {
    void update(int temperature, int humidity, int pressure);
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

interface DisplayElement {
    void display();
}


