package com.MyFirstJavaProject.blogging_app.Controller;

import com.MyFirstJavaProject.blogging_app.Entity.WeatherInfo;
import com.MyFirstJavaProject.blogging_app.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/city/{cityName}")
    public WeatherInfo getWeatherInfo(@PathVariable String cityName) {
        return weatherService.getWeatherInfo(cityName);
    }
}
