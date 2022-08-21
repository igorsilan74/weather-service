package com.is.weather.external.openweather.model;

import com.google.gson.annotations.SerializedName;

public class Rain3h {
    @SerializedName("3h")
    private double mm;

    public double getMm() {
        return mm;
    }

    public void setMm(double mm) {
        this.mm = mm;
    }
}
