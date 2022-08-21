package com.is.weather.schedulingtasks;

import com.is.weather.external.openweather.model.Weather;
import com.is.weather.external.openweather.service.OpenWeatherService;
import com.is.weather.model.City;
import com.is.weather.model.CityWeather;
import com.is.weather.repository.CityRepository;
import com.is.weather.repository.CityWeatherRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduledTasks {

  private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  private final OpenWeatherService openWeatherService;

  private final CityRepository cityRepository;
  private final CityWeatherRepository cityWeatherRepository;

  @Scheduled(fixedRateString ="${service.fetchWeather}")
  public void reportCurrentTime() {
    log.info("Getting weather from external service at {}", dateFormat.format(new Date()));
    List<City> cities = cityRepository.findAll();

    for (City city : cities) {
      Weather[] weatherData = openWeatherService.fetchWeather(city);
      // and another services
      if (weatherData != null) {
        saveWeatherToDB(weatherData, city);
      }
    }

  }

  @Transactional
  public void saveWeatherToDB(Weather[] wearherArr, City city) {

    if (wearherArr.length > 0) {
      List<Weather> weathers = Arrays.asList(wearherArr)
          .stream()
          .filter(weather ->
              Instant.ofEpochMilli(weather.getDt() * 1000).atZone(ZoneId.of("UTC")).toLocalDate()
                  .isEqual(LocalDate.now())
          )
          .sorted((weather1, weather2) -> Long.compare(weather2.getDt(), weather1.getDt()))
          .collect(Collectors.toList());

      CityWeather weather = CityWeather
          .builder()
          .city(city)
          .dateMeasurement(LocalDateTime.now())
          .temperature(
              new BigDecimal(
                  (weathers.get(0).getMain().getTemp_min() + weathers.get(0).getMain().getTemp_max())/2
              ).setScale(2, RoundingMode.HALF_UP).doubleValue()
          )
          .build();

      cityWeatherRepository.save(weather);
    }
  }

}