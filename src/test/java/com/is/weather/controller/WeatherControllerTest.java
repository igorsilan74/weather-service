package com.is.weather.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.weather.model.CityWeatherDto;
import com.is.weather.service.WeatherService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {
  private MockMvc mockMvc;

  @Mock
  private WeatherService weatherService;

  @InjectMocks
  private WeatherController weatherController;

  @Test
  void findCityWeatherByCityNameAndDate_cityAndDate_returnedSuccessfully() throws Exception {

    LocalDateTime dateMeasurement = LocalDateTime.now();
    String country = "country";
    String city = "city";

    mockMvc = MockMvcBuilders.standaloneSetup(this.weatherController).build();
    List<CityWeatherDto> expectedDtoList = List.of(
        CityWeatherDto.builder()
            .id(Long.valueOf(1))
            .city(city + "(" + country + ")")
            .dateMeasurement(dateMeasurement)
            .temperature(Double.valueOf(20))
            .build()
    );

    Mockito.when(weatherService.findCityWeathersByCityAndDate(country, city, dateMeasurement.toLocalDate())).thenReturn(expectedDtoList);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    MockHttpServletResponse actual = mockMvc
        .perform(
            get("/api/weather/{country}/{city}/{date}", country, city, dateMeasurement.toLocalDate().format(formatter))
        )
        .andExpect(status().isOk())
        .andReturn().getResponse();

    Assertions.assertEquals(new ObjectMapper().writeValueAsString(expectedDtoList), actual.getContentAsString());

  }

  @Test
  void findCityWeatherByCityName_city_returnedSuccessfully() throws Exception {

    LocalDateTime dateMeasurement = LocalDateTime.now();
    String country = "country";
    String city = "city";

    mockMvc = MockMvcBuilders.standaloneSetup(this.weatherController).build();
    List<CityWeatherDto> expectedDtoList = List.of(
        CityWeatherDto.builder()
            .id(Long.valueOf(1))
            .city(city + "(" + country + ")")
            .dateMeasurement(dateMeasurement)
            .temperature(Double.valueOf(20))
            .build()
    );

    Mockito.when(weatherService.findCityWeatherByCity(country,city)).thenReturn(expectedDtoList);

    MockHttpServletResponse actual = mockMvc
        .perform(
            get("/api/weather/{country}/{city}", country, city)
        )
        .andExpect(status().isOk())
        .andReturn().getResponse();

    Assertions.assertEquals(new ObjectMapper().writeValueAsString(expectedDtoList), actual.getContentAsString());

  }

}


