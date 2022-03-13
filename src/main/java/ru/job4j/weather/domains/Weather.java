package ru.job4j.weather.domains;

import java.util.Objects;

public class Weather {

    private int id;
    private String city;
    private int temperature;

    public Weather(int aId, String aCity, int aTemperature) {
        id = aId;
        city = aCity;
        temperature = aTemperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weather weather = (Weather) o;
        return id == weather.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
