# Weather Monitoring System

### Objective
The **Weather Monitoring System** is a real-time data processing application designed to monitor weather conditions and provide summarized insights through rollups and aggregates. The system fetches weather data from the OpenWeatherMap API, processes it for key cities in India, and generates daily summaries, visualizations, and alerts based on user-defined thresholds.

### Data Source
The application continuously retrieves real-time weather data from the OpenWeatherMap API for the following metro cities in India:
- Delhi
- Mumbai
- Chennai
- Bangalore
- Kolkata
- Hyderabad

The data retrieved includes:
- **Main**: Main weather condition (e.g., Rain, Snow, Clear).
- **Temp**: Current temperature in Celsius.
- **Feels_like**: Perceived temperature in Celsius.
- **Dt**: Time of the data update (Unix timestamp).

### Features

1. **Real-Time Data Retrieval**: 
   - Fetches weather data at a configurable interval (Every 5 minutes).
   - Converts temperature values from Kelvin to Celsius.

2. **Daily Weather Summary**:
   - Rolls up the weather data for each day.
   - Calculates daily aggregates such as:
     - Average temperature.
     - Maximum temperature.
     - Minimum temperature.
     - Dominant weather condition.
    - Note: The daily weather summary is calculated at the start of the next day based on the data gathered throughout the current day.

3. **Alerting System**:
   - User-defined thresholds for temperature or weather conditions.
   - Triggers alerts when conditions (Temperature exceeding 35°C) are breached for consecutive 2 updates.

4. **Data Persistence**:
   - Stores daily weather summaries for further analysis in a database.

5. **Visualization**:
   - Displays daily summaries, historical trends, and triggered alerts.

---

### Technologies Used
- **Java 17.0.12**: The core programming language.
- **Spring Boot**: Framework for building the application.
- **MySQL**: For persistent storage of weather summaries.
- **OpenWeatherMap API**: Source of real-time weather data.
- **Maven**: Build tool for managing dependencies.


### Installation

#### Prerequisites
- **Java 17.0.12**
- **Maven**: For building the project.
- **MySQL**: For storing daily weather summaries.
- **OpenWeatherMap API Key**: You’ll need to sign up and get a free API key from [OpenWeatherMap](https://openweathermap.org/).

#### Steps to Run the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/nitishparimi/WeatherMonitoring.git
   ```

2. Navigate to the project directory:
   ```bash
   cd WeatherMonitoring
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Configure your `application.properties` file and `WeatherService.java`:
   
   - Create database in MySQL
     ```Query
     create database weather_db;
     ```
   
   - Add your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     ```

   - Add your API Key from OpenWeather API Key in `src/main/java/com/example/demo/WeatherService.java`:
     ```Code
     private final String apiKey = " -- Your Open Weather API Key";
     ```

6. Run the application:
   ```bash
   mvn spring-boot:run
   ```

7. Access the dashboard at:
   ```
   http://localhost:9000/weather
   ```

---

### Usage

#### Real-Time Weather Monitoring:
- The system retrieves weather data for the selected cities every 5 minutes.
- It processes and stores the data, updating the web-based dashboard for users to view.

#### Custom Alerts:
- Users can set thresholds (e.g., temperature exceeding 35°C).
- If thresholds are breached, alerts will be triggered and displayed on the Console.

#### Visualizations:
- View historical weather trends and alerts on the dashboard.

---

### Contact

- **Name**: Nitish Parimi
- **GitHub**: [nitishparimi](https://github.com/nitishparimi)

---
