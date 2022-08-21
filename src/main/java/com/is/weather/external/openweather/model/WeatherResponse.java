package com.is.weather.external.openweather.model;

import java.util.Arrays;

public class WeatherResponse {
    private String cod;
    private String message;
    private City city;
    private int cnt;
    private Weather[] list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Weather[] getList() {
        return list;
    }

    public void setList(Weather[] list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "cod='" + cod + '\'' +
                ", message='" + message + '\'' +
                ", city=" + city +
                ", cnt=" + cnt +
                ", list=" + Arrays.toString(list) +
                '}';
    }


}
