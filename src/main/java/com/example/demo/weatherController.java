package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class weatherController {

    private final Map<String, Integer> consecutiveBreaches = new HashMap<>();

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherSummaryRepository weatherSummaryRepository;


    @Autowired
    private DailyWeatherSummaryRepository dailyWeatherSummaryRepository;
    
    @Autowired
    private AlertService alertService;

    @GetMapping("/welcome")
    public String fun1() {
        return "welcome.jsp";
    }

    @Scheduled(fixedRate = 300000)  // Fetch every 5 minutes
    public void fetchWeatherDataForMetros() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
        final double temperatureThreshold = 35.0;

        for (String city : cities) {
            WeatherData weatherData = weatherService.getWeatherForCity(city);
            double tempInCelsius = weatherData.getMain().getTemp() - 273.15;
            tempInCelsius = Math.ceil(tempInCelsius);
            String weatherCondition = weatherData.getWeather()[0].getMain();
            Optional<WeatherSummary> existingWeatherSummaryOpt = weatherSummaryRepository.findByCity(city);

            try {
                WeatherSummary weatherSummary;
                if (existingWeatherSummaryOpt.isPresent()) {
                    weatherSummary = existingWeatherSummaryOpt.get();
                } else {
                    weatherSummary = new WeatherSummary();
                    weatherSummary.setCity(city);
                }

                weatherSummary.setTemperature(tempInCelsius);
                weatherSummary.setTimestamp(LocalDateTime.now());
                weatherSummary.setWeatherCondition(weatherCondition);
                weatherSummaryRepository.save(weatherSummary);
                

                if (tempInCelsius > temperatureThreshold) {
                    int breachCount = consecutiveBreaches.getOrDefault(city, 0) + 1;
                    consecutiveBreaches.put(city, breachCount);

                    if (breachCount >= 2) {
                        // Trigger alerts here
                        System.out.println(" ..................... ");
                    	System.out.println("thresholde breach in the" + city + " .");
                        System.out.println(" ..................... ");
                    }
                } else {
                    consecutiveBreaches.put(city, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   
    @GetMapping("/weather")
    public String showWeatherPage(Model model) {
        try {
            List<WeatherSummary> weatherSummaries = weatherSummaryRepository.findAll();
            model.addAttribute("weatherSummaries", weatherSummaries);
        } catch (Exception e) {
            e.printStackTrace();
            return "welcome.jsp";
        }
        return "weather.jsp";
    }
    
    @GetMapping("/dailyWeather")
    public String showDailyWeatherPage(Model model) {
        List<DailyWeatherSummary> dailySummaries = dailyWeatherSummaryRepository.findAll();
        model.addAttribute("dailySummaries", dailySummaries);
        return "dailyWeather.jsp"; 
    }
  
}
