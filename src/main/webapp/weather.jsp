<%@page import="com.example.demo.WeatherSummary"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weather Data</title>
    <meta http-equiv="refresh" content="60"> <!-- Refresh every 5 minutes -->
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

    <h1>Current Weather Data</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>City</th>
            <th>Temperature (°C)</th>
            <th>Time</th>
        </tr>
        <% 
            List<WeatherSummary> weatherSummaries = (List<WeatherSummary>) request.getAttribute("weatherSummaries");
            if (weatherSummaries != null) {
                for (WeatherSummary s : weatherSummaries) { 
        %>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getCity() %></td>
                <td><%= s.getTemperature() %></td>
                <td><%= s.getTimestamp() %></td>
            </tr>
        <% 
                } 
            } 
        %>
        
    </table>
    <a href="/dailyWeather">Calculate Today's Weather Summary</a>
    
</body>
</html>
