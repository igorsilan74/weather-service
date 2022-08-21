package com.is.weather.external.openweather;

import com.google.gson.Gson;
import com.is.weather.external.openweather.model.MainInfo;
import com.is.weather.external.openweather.model.Weather;
import com.is.weather.external.openweather.model.WeatherResponse;
import com.is.weather.external.openweather.service.OpenWeatherService;
import com.is.weather.external.openweather.service.ResponseService;
import com.is.weather.model.City;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherServiceTest {

  @Mock
  private ResponseService responseService;

  @InjectMocks
  private OpenWeatherService openWeatherService;

  @Test
  void fetchWeather_city_Weather() {

    City city = City.builder().id(Long.valueOf(1)).country("country").name("city").weatherId(1).build();
    Gson gson = new Gson();

    WeatherResponse expected = new WeatherResponse();
    Long timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    expected.setList(new Weather[]{new Weather(timestamp, new MainInfo(18,20))});

    Mockito.when(responseService.getResponse(city.getWeatherId())).thenReturn(gson.toJson(expected));

    Weather[] actual = openWeatherService.fetchWeather(city);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected.getList()[0], actual[0]);

  }
}