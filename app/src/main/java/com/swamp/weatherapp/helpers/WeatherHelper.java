package com.swamp.weatherapp.helpers;

import com.swamp.weatherapp.model.WeatherInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.nio.file.Paths.get;

public class WeatherHelper {

    private static final String API_KEY = "b94b3ef9ee3f852401b2830a3787fadd";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat={LAT}&lon={LON}&APPID={API_KEY}";

    public static WeatherInfo getWeatherInfo(double latitude, double longitude) {
        WeatherInfo weatherInfo = new WeatherInfo();

        String data = getWeatherData(latitude, longitude);
        weatherInfo = parseWeather(data);

        return weatherInfo;
    }

    private static String getWeatherData(double latitude, double longitude) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        String data = null;

        String url = BASE_URL.replace("{API_KEY}", API_KEY).
                replace("{LAT}", String.valueOf(latitude)).
                replace("{LON}", String.valueOf(longitude));

        try {
            connection = (HttpURLConnection) (new URL(url)).openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();

            StringBuffer buffer = new StringBuffer();
            inputStream = new BufferedInputStream(connection.getInputStream());// connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            while((line = br.readLine()) != null) {
                buffer.append(line + "rn");
            }

            inputStream.close();
            connection.disconnect();

            data = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { inputStream.close(); } catch(Throwable t) {}
            try { connection.disconnect(); } catch(Throwable t) {}
        }

        return data;
    }

    private static WeatherInfo parseWeather(String data) {
        WeatherInfo weatherInfo = new WeatherInfo();

        if (data != null) {
            try {
                JSONObject json = new JSONObject(data);
                JSONObject main = json.getJSONObject("main");
                double temp = main.getDouble("temp");
                double humidity = main.getDouble("humidity");
                double pressure = main.getDouble("pressure");

                JSONObject weather_json = json.getJSONArray("weather").getJSONObject(0);
                String weather = weather_json.getString("description");

                JSONObject wind_json = json.getJSONObject("wind");
                double wind_speed = wind_json.getDouble("speed");


                JSONObject sys = json.getJSONObject("sys");
                String country = sys.getString("country");
                String city = json.getString("name");

                weatherInfo.setPlace(city + " - " + country);
                weatherInfo.setTemperature(temp);
                weatherInfo.setHumidity(humidity);
                weatherInfo.setPressure(pressure);
                weatherInfo.setCloudiness(weather);
                weatherInfo.setWind(String.valueOf(wind_speed));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return weatherInfo;
    }
}
