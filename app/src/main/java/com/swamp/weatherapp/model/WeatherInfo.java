package com.swamp.weather.model;

public class WeatherInfo {

    private double temperature;
    private double precipitation;
    private double humidity;
    private String wind;
    private String cloudiness;

    public WeatherInfo() {
        // Empty constructor
    }

    public WeatherInfo(double temperature, double precipitation, double humidity, String wind, String cloudiness) {
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.wind = wind;
        this.cloudiness = cloudiness;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
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
                "Precipitação: " + String.valueOf(precipitation)  + "\n" +
                "Umidade: " + String.valueOf(humidity) + "\n" +
                "Vento: " + wind + "\n" +
                "Nebulosidade: " + cloudiness + "\n";
    }
}
