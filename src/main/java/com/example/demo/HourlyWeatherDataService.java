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

        	double temperature = weatherData.getMain().getTemp() - 273.15;
        	temperature = Math.ceil(temperature);;

        	String weatherCondition = weatherData.getWeather()[0].getMain(); 

        	HourlyWeather hourlyWeather = new HourlyWeather();
        	hourlyWeather.setCity(city);
        	hourlyWeather.setTemperature(temperature);
        	hourlyWeather.setWeatherCondition(weatherCondition);
        	hourlyWeather.setTimestamp(currentTime); 

        	hourlyWeatherRepository.save(hourlyWeather);

        }
    }
}
