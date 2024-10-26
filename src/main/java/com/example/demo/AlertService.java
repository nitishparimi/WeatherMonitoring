package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class AlertService {

    public boolean checkTemperatureThreshold(double currentTemp, double threshold) {
        return currentTemp > threshold;
    }
}