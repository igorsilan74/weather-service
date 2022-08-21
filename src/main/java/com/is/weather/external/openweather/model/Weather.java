package com.is.weather.external.openweather.model;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.Objects;

public class Weather {
    private long dt;
    private MainInfo main;
    @SerializedName("weather")
    private WeatherType[] weather;
    private Clouds clouds;
    private Wind wind;
    private Rain3h rain;
    private Snow3h snow;

    public Weather(long dt, MainInfo main) {
        this.dt = dt;
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public WeatherType[] getWether() {
        return weather;
    }

    public void setWether(WeatherType[] wether) {
        this.weather = wether;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Rain3h getRain() {
        return rain;
    }

    public void setRain(Rain3h rain) {
        this.rain = rain;
    }

    public Snow3h getSnow() {
        return snow;
    }

    public void setSnow(Snow3h snow) {
        this.snow = snow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof com.is.weather.external.openweather.model.Weather))
            return false;
        Weather weather = (Weather) o;
        return  Objects.equals(this.dt, weather.dt) &&
                Objects.equals(this.main, weather.main) &&
                Objects.equals(this.weather, weather.weather) &&
                Objects.equals(this.clouds, weather.clouds) &&
                Objects.equals(this.wind, weather.wind) &&
                Objects.equals(this.rain, weather.rain) &&
                Objects.equals(this.snow, weather.snow);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.dt, this.main, this.weather, this.clouds, this.wind, this.rain, this.snow);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "dt=" + dt +
                ", main=" + main +
                ", weather=" + Arrays.toString(weather) +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", Rain3h=" + rain +
                ", snow3h=" + snow +
                '}';
    }

}
