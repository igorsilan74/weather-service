package com.is.weather;

import com.is.weather.model.City;
import com.is.weather.repository.CityRepository;
import com.is.weather.repository.CityWeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(CityRepository repository, CityWeatherRepository weatherRepository) {

    return args -> {
      log.info("Preloading " + repository.save(new City("Chelyabinsk", "Russia", 1508291)));
      log.info("Preloading " + repository.save(new City("Kherson", "Ukraine", 706448)));
    };
  }
}