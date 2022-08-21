package com.is.weather.external.openweather.model;

import java.util.Objects;

public class MainInfo {
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;

    public MainInfo(double temp_min, double temp_max) {
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }
    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof com.is.weather.external.openweather.model.MainInfo))
            return false;
        MainInfo mainInfo = (MainInfo) o;
        return  Objects.equals(this.temp_min, mainInfo.temp_min) &&
            Objects.equals(this.temp_max, mainInfo.temp_max) &&
            Objects.equals(this.pressure, mainInfo.pressure) &&
            Objects.equals(this.humidity, mainInfo.humidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.temp_min, this.temp_max, this.pressure, this.humidity);
    }

    @Override
    public String toString() {
        return "MainInfo{" +
            "temp_min=" + temp_min +
            ", temp_max=" + temp_max +
            ", pressure=" + pressure +
            ", humidity=" + humidity +
            '}';
    }
}
