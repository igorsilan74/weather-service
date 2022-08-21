package com.is.weather.service;

import com.is.weather.mapper.CityWeatherMapper;
import com.is.weather.model.CityWeather;
import com.is.weather.model.CityWeatherDto;
import com.is.weather.repository.CityWeatherRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

  private final CityWeatherRepository cityWeatherRepository;
  private final CityWeatherMapper cityWeatherMapper;

  public List<CityWeatherDto> findCityWeathersByCityAndDate(
      String country,
      String city,
      LocalDate measurementDate
  ) {
    return cityWeatherMapper.mapToCityWeathersDto(
        cityWeatherRepository.findByCityCountryAndCityNameAndDateMeasurementBetweenOrderByDateMeasurementDesc(
            capitalize(country),
            capitalize(city),
            measurementDate.atTime(0, 0, 0),
            measurementDate.atTime(23, 59, 59)
        )
    );
  }

  public List<CityWeatherDto> findCityWeatherByCity(String country, String city) {

    List<CityWeather> cityWeathers = cityWeatherRepository.findByCityCountryAndCityNameOrderByDateMeasurementDesc(
            capitalize(country),
            capitalize(city)
    );

    return cityWeathers.isEmpty()
        ? Collections.emptyList()
        : cityWeatherMapper.mapToCityWeathersDto(List.of(cityWeathers.get(0)));

  }

  private String capitalize(String param) {
    return param.substring(0, 1).toUpperCase() + param.substring(1).toLowerCase();
  }

}
