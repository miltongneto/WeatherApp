package com.swamp.weatherapp.model;

public class WeatherInfo {

    private String place;
    private double temperature;
    private int humidity;
    private double pressure;
    private String wind;
    private String cloudiness;

    public WeatherInfo() {
        // Empty constructor
    }

    public WeatherInfo(String place, double temperature, int humidity, double pressure,
                       String wind, String cloudiness) {
        this.place = place;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
        this.cloudiness = cloudiness;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }


    @Override
    public String toString() {
        return "Temperatura: " + String.valueOf(temperature) + "\n" +
                "Umidade: " + String.valueOf(humidity) + "\n" +
                "Vento: " + wind + "\n" +
                "Nebulosidade: " + cloudiness + "\n";
    }
}
