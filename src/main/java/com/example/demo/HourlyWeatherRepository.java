package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface HourlyWeatherRepository extends JpaRepository<HourlyWeather, Long> {
    List<HourlyWeather> findByCityAndTimestampBetween(String city, LocalDateTime start, LocalDateTime end);
}
