package com.swamp.weatherapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.swamp.weather.helpers.WeatherHelper;
import com.swamp.weather.model.WeatherInfo;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private WeatherInfo weatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " - " + latLng.longitude);
                markerOptions.snippet(latLng.latitude + " : " + latLng.longitude);

                mMap.clear();

                Marker marker = mMap.addMarker(markerOptions);
                getWeatherInfo(marker);
            }
        });
    }

    public void getWeatherInfo(final Marker marker) {
        final LatLng position = marker.getPosition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                weatherInfo = WeatherHelper.getWeatherInfo(
                        position.latitude, position.longitude);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        marker.setSnippet(weatherInfo.toString());
                        marker.showInfoWindow();
                        Toast.makeText(getApplicationContext(),
                                weatherInfo.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();

    }

}
