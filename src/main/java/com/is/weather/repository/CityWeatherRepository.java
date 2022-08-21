package com.is.weather.repository;

import com.is.weather.model.CityWeather;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityWeatherRepository extends JpaRepository<CityWeather, Long> {
  List<CityWeather> findByCityCountryAndCityNameAndDateMeasurementBetweenOrderByDateMeasurementDesc(
      String country,
      String city,
      LocalDateTime start,
      LocalDateTime end
  );

  List<CityWeather> findByCityCountryAndCityNameOrderByDateMeasurementDesc(String country, String city);

}
