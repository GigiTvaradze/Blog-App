package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weatherperson.api.key}")
    private String apiKey;

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherInfo getWeatherInfo(String cityName) {
        String apiUrl = String.format("%s?q=%s&appid=%s", API_URL, cityName, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, WeatherInfo.class);
    }
}
