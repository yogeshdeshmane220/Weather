package com.example.weatherapplication.seasonData;

import java.util.List;

public class mausamData {
    private List<weather> weather;
    private main main;
    private String name;

    public mausamData(List<com.example.weatherapplication.seasonData.weather> weather, com.example.weatherapplication.seasonData.main main, String name) {
        this.weather = weather;
        this.main = main;
        this.name = name;
    }

    public List<com.example.weatherapplication.seasonData.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.example.weatherapplication.seasonData.weather> weather) {
        this.weather = weather;
    }

    public com.example.weatherapplication.seasonData.main getMain() {
        return main;
    }

    public void setMain(com.example.weatherapplication.seasonData.main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
