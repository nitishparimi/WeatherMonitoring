package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DailyWeatherRollupService {

    @Autowired
    private HourlyWeatherRepository hourlyWeatherRepository;

    @Autowired
    private DailyWeatherSummaryRepository dailyWeatherSummaryRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void rollUpDailyWeather() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
        LocalDate yesterday = LocalDate.now().minusDays(1);
        
        for (String city : cities) {
            // Get hourly weather data for the entire day
            LocalDateTime startOfDay = yesterday.atStartOfDay();
            LocalDateTime endOfDay = yesterday.atTime(23, 59, 59);
            List<HourlyWeather> hourlyWeatherList = hourlyWeatherRepository.findByCityAndTimestampBetween(city, startOfDay, endOfDay);
            
            if (hourlyWeatherList.isEmpty()) {
                continue;
            }

            // Calculate aggregates
            double avgTemp = hourlyWeatherList.stream().mapToDouble(HourlyWeather::getTemperature).average().orElse(0.0);
            double maxTemp = hourlyWeatherList.stream().mapToDouble(HourlyWeather::getTemperature).max().orElse(0.0);
            double minTemp = hourlyWeatherList.stream().mapToDouble(HourlyWeather::getTemperature).min().orElse(0.0);
            
            // Determine the dominant weather condition
            Map<String, Long> conditionCount = hourlyWeatherList.stream()
                .collect(Collectors.groupingBy(HourlyWeather::getWeatherCondition, Collectors.counting()));
            String dominantCondition = conditionCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");

            // Save the daily summary
            DailyWeatherSummary dailySummary = new DailyWeatherSummary();
            dailySummary.setCity(city);
            dailySummary.setDate(yesterday);
            dailySummary.setAvgTemperature(avgTemp);
            dailySummary.setMaxTemperature(maxTemp);
            dailySummary.setMinTemperature(minTemp);
            dailySummary.setDominantWeatherCondition(dominantCondition);

            dailyWeatherSummaryRepository.save(dailySummary);
        }
    }
}
