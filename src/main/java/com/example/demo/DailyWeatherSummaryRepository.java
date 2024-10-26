package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWeatherSummaryRepository extends JpaRepository<DailyWeatherSummary, Long> {
    List<DailyWeatherSummary> findByCity(String city);
}
