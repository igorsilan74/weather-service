package com.is.weather.service;

import com.is.weather.mapper.CityWeatherMapper;
import com.is.weather.model.City;
import com.is.weather.model.CityWeather;
import com.is.weather.model.CityWeatherDto;
import com.is.weather.repository.CityWeatherRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

  @Mock
  private CityWeatherRepository cityWeatherRepository;

  @Mock
  private CityWeatherMapper cityWeatherMapper;

  @InjectMocks
  private WeatherService weatherService;

  @Test
  void findCityWeathersByCityAndDate_cityAndDate_cityWeatherDtoList() {

    LocalDateTime dateMeasurement = LocalDateTime.now();
    String country = "country";
    String city = "city";

    List<CityWeather> expected = List.of(
        CityWeather.builder()
            .id(Long.valueOf(1))
            .city(City.builder().id(Long.valueOf(1)).country(country).name(city).build())
            .dateMeasurement(dateMeasurement)
            .temperature(Double.valueOf(20))
            .build()
    );

    List<CityWeatherDto> expectedDtoList = List.of(
        CityWeatherDto.builder()
            .id(Long.valueOf(1))
            .city(city + "(" + country + ")")
            .dateMeasurement(dateMeasurement)
            .temperature(Double.valueOf(20))
            .build()
    );

    Mockito.when(cityWeatherRepository.findByCityCountryAndCityNameAndDateMeasurementBetweenOrderByDateMeasurementDesc(
        capitalize(country),
        capitalize(city),
        dateMeasurement.toLocalDate().atTime(0, 0, 0),
        dateMeasurement.toLocalDate().atTime(23, 59, 59)
    )).thenReturn(expected);

    Mockito.when(cityWeatherMapper.mapToCityWeathersDto(expected)).thenReturn(expectedDtoList);

    List<CityWeatherDto> actual = weatherService.findCityWeathersByCityAndDate(country, city, dateMeasurement.toLocalDate());

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expectedDtoList, actual);
  }

  @Test
  void findCityWeathersByCity_city_cityWeatherDtoList() {

    LocalDateTime dateMeasurement = LocalDateTime.now();
    String country = "country";
    String city = "city";

    List<CityWeather> expected = List.of(
        CityWeather.builder()
            .id(Long.valueOf(1))
            .city(City.builder().id(Long.valueOf(1)).country(country).name(city).build())
            .dateMeasurement(dateMeasurement)
            .temperature(Double.valueOf(20))
            .build()
    );

    List<CityWeatherDto> expectedDtoList = List.of(
        CityWeatherDto.builder()
            .id(Long.valueOf(1))
            .city(city + "(" + country + ")")
            .dateMeasurement(dateMeasurement)
            .temperature(Double.valueOf(20))
            .build()
    );

    Mockito.when(cityWeatherRepository.findByCityCountryAndCityNameOrderByDateMeasurementDesc(
        capitalize(country),
        capitalize(city)
    )).thenReturn(expected);

    Mockito.when(cityWeatherMapper.mapToCityWeathersDto(expected)).thenReturn(expectedDtoList);

    List<CityWeatherDto> actual = weatherService.findCityWeatherByCity(country, city);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expectedDtoList, actual);
  }

  private String capitalize(String param) {
    return param.substring(0, 1).toUpperCase() + param.substring(1).toLowerCase();
  }

}
