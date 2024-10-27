package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.WeatherData;

@Service
public class WeatherService {
	private final String apiKey = " -- Your Open Weather API Key";
    private final String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherData getWeatherForCity(String city) {
        String url = apiUrl.replace("{city}", city).replace("{apiKey}", apiKey);
        return restTemplate.getForObject(url, WeatherData.class);
    }
}
