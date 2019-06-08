package com.swamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.swamp.weatherapp.R;

public class InfoWindowCustomAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    LayoutInflater inflater;

    public InfoWindowCustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.info_window_layout_custom, null);
        TextView txt_tittle = view.findViewById(R.id.info_window_txt_title);
        TextView txt_info = view.findViewById(R.id.info_window_txt_content);

        txt_tittle.setText(marker.getTitle());
        txt_info.setText(marker.getSnippet());

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
