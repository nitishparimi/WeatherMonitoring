package com.example.demo;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HourlyWeatherDataService {

    @Autowired
    private WeatherService weatherApiService; // Service to call external weather API

    @Autowired
    private HourlyWeatherRepository hourlyWeatherRepository;

    @Scheduled(cron = "0 0 * * * ?") // Runs every hour at minute 0
    public void fetchAndSaveHourlyWeatherData() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
        LocalDateTime currentTime = LocalDateTime.now();

        for (String city : cities) {
        	WeatherData weatherData = weatherApiService.getWeatherForCity(city);

        	// Get temperature from "main"
        	double temperature = weatherData.getMain().getTemp() - 273.15;
        	temperature = Math.ceil(temperature);;

        	// Get the main weather condition from the first entry in the "weather" array
        	String weatherCondition = weatherData.getWeather()[0].getMain(); 

        	// Now you can set these values in the HourlyWeather entity
        	HourlyWeather hourlyWeather = new HourlyWeather();
        	hourlyWeather.setCity(city);
        	hourlyWeather.setTemperature(temperature);
        	hourlyWeather.setWeatherCondition(weatherCondition);
        	hourlyWeather.setTimestamp(currentTime); // Set the current timestamp

        	// Save to repository
        	hourlyWeatherRepository.save(hourlyWeather);

        }
    }
}
