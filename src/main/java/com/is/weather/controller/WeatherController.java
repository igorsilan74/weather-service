package com.is.weather.controller;

import com.is.weather.model.CityWeatherDto;
import com.is.weather.service.WeatherService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather")
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherService weatherService;

  @GetMapping(value = {"/{country}/{city}", "/{country}/{city}/{date}"})
  public List<CityWeatherDto> getWeather(
      @PathVariable("country") String country,
      @PathVariable("city") String city,
      @PathVariable(name = "date", required = false) String date
  ) {
    LocalDate dateMeasurement = null;
    try {
      dateMeasurement = LocalDate.parse(date);
    } catch (Exception e) {}

    if (dateMeasurement != null) {
      return weatherService.findCityWeathersByCityAndDate(country, city, dateMeasurement);
    } else {
      return weatherService.findCityWeatherByCity(country, city);
    }
  }

}