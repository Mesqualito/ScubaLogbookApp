package com.andreahowes.dive_db.logic.weather;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeatherFromLocation(String location) {

        return weatherRepository.getWeather(location);
    }

}
