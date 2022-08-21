package com.is.weather.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityWeather {

  private @Id
  @GeneratedValue Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "city_id")
  private City city;

  private LocalDateTime dateMeasurement;

  private Double temperature;

  public CityWeather(City city, LocalDateTime dateMeasurement, Double temperature) {
    this.city = city;
    this.dateMeasurement = dateMeasurement;
    this.temperature = temperature;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public LocalDateTime getDateMeasurement() {
    return dateMeasurement;
  }

  public void setDateMeasurement(LocalDateTime dateMeasurement) {
    this.dateMeasurement = dateMeasurement;
  }

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

}
