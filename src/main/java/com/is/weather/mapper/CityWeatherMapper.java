package com.is.weather.mapper;

import com.is.weather.model.CityWeather;
import com.is.weather.model.CityWeatherDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CityWeatherMapper {

  public List<CityWeatherDto> mapToCityWeathersDto(List<CityWeather> cityWeathers) {
       if (cityWeathers == null) {
         return null;
       }

       List<CityWeatherDto> list = new ArrayList<CityWeatherDto>(cityWeathers.size());
       for (CityWeather cityWeather : cityWeathers) {
         list.add(mapToCityWeatherDto(cityWeather));
       }
       return list;
  }

  private CityWeatherDto mapToCityWeatherDto(CityWeather cityWeather)
  {
    if ( cityWeather == null ) {
      return null;
    }

    return CityWeatherDto
        .builder()
        .city(cityWeather.getCity().getName() + "(" + cityWeather.getCity().getCountry() + ")")
        .id(cityWeather.getId())
        .dateMeasurement(cityWeather.getDateMeasurement())
        .temperature(cityWeather.getTemperature())
        .build();
  }

}
