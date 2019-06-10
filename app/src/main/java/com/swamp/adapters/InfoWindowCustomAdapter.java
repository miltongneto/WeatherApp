package com.swamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.swamp.weatherapp.R;
import com.swamp.weatherapp.model.WeatherInfo;


public class InfoWindowCustomAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    LayoutInflater inflater;

    public InfoWindowCustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.info_window_layout_custom, null);
        TextView txt_tittle = view.findViewById(R.id.info_window_txt_title);

        TextView txt_temp = view.findViewById(R.id.info_window_txt_temp);
        TextView txt_humidity = view.findViewById(R.id.info_window_txt_humidity);
        TextView txt_wind = view.findViewById(R.id.info_window_txt_wind);
        TextView txt_pressure = view.findViewById(R.id.info_window_txt_pressure);
        TextView txt_cloudiness = view.findViewById(R.id.info_window_txt_cloudiness);


        Gson gson = new Gson();
        WeatherInfo weatherInfo = gson.fromJson(marker.getSnippet(), WeatherInfo.class);

        txt_tittle.setText(marker.getTitle());
        txt_temp.setText(Math.round(weatherInfo.getTemperature() - 273.15) + " ºC");
        txt_humidity.setText(weatherInfo.getHumidity() + "%");
        txt_wind.setText(weatherInfo.getWind() + " m/s");
        txt_pressure.setText(Math.round(weatherInfo.getPressure()) + " hpa");
        txt_cloudiness.setText(weatherInfo.getCloudiness());

        return view;
    }
}
