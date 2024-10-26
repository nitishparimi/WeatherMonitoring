package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeatherSummaryRepository extends JpaRepository<WeatherSummary, Long> {
    List<WeatherSummary> findAll();
    Optional<WeatherSummary> findByCity(String city);
    //List<WeatherSummary> findByCityAndRecordedDate(String city, LocalDate recordedDate);
    
    @Query("SELECT w FROM WeatherSummary w WHERE w.city = :city AND w.timestamp BETWEEN :startOfDay AND :endOfDay")
    List<WeatherSummary> findByCityAndTimestamp(
            @Param("city") String city,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
}
