package com.example.demo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DailyWeatherSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private LocalDate date;
    private double avgTemperature;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getAvgTemperature() {
		return avgTemperature;
	}
	public void setAvgTemperature(double avgTemperature) {
		this.avgTemperature = avgTemperature;
	}
	public double getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public double getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}
	public String getDominantWeatherCondition() {
		return dominantWeatherCondition;
	}
	public void setDominantWeatherCondition(String dominantWeatherCondition) {
		this.dominantWeatherCondition = dominantWeatherCondition;
	}
	private double maxTemperature;
    private double minTemperature;
    private String dominantWeatherCondition;
}
