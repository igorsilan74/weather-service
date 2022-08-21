package com.is.weather.external.openweather.service;

import com.google.gson.Gson;
import com.is.weather.external.openweather.model.Weather;
import com.is.weather.external.openweather.model.WeatherResponse;
import com.is.weather.model.City;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenWeatherService {

  private static final Logger log = LoggerFactory.getLogger(OpenWeatherService.class);

  private final ResponseService responseService;

  @Transactional
  public Weather[] fetchWeather(City city) {
    System.out.println("Open weather service fetching data for city " + city.getName());
    try {
      Gson gson = new Gson();
      WeatherResponse weatherResponse = gson.fromJson(responseService.getResponse(city.getWeatherId()), WeatherResponse.class);
      if (weatherResponse != null) {
        return weatherResponse.getList();
      }
    } catch (Exception e) {
      log.error("Can't fetch weather data for city " + city.getName());
    }
    return null;
  }

}
