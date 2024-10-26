<%@page import="com.example.demo.DailyWeatherSummary"%>
<%@page import="com.example.demo.WeatherSummary"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daily Weather Summary</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        h1 {
            background-color: #3498db;
            color: white;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #3498db;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>

    <h1>Daily Weather Summary</h1>

    <table>
        <tr>
            <th>City</th>
            <th>Date</th>
            <th>Average Temperature (°C)</th>
            <th>Maximum Temperature (°C)</th>
            <th>Minimum Temperature (°C)</th>
            <th>Dominant Weather Condition</th>
        </tr>
         <% 
            List<DailyWeatherSummary> weatherSummaries = (List<DailyWeatherSummary>) request.getAttribute("dailySummaries");
            if (weatherSummaries != null) {
                for (DailyWeatherSummary s : weatherSummaries) { 
        %>
       <tr>
                <td><%= s.getCity() %></td>
                <td><%= s.getDate() %></td>
                <td><%= s.getAvgTemperature() %></td>
                <td><%= s.getMaxTemperature() %></td>
                <td><%= s.getMinTemperature() %></td>
                <td><%= s.getDominantWeatherCondition() %></td>
                
        </tr>
        <% 
                } 
            } 
        %>
    </table>

    <a href="/weather">View Current Weather Data</a>

</body>
</html>
