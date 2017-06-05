package com.example.enriquebecerram.partyapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Geocoder geocoder;
    GoogleMap map;
    private GoogleMap mMap;
    //PositionActual
    LocationManager locationManager;
    List<LatLng> markerPositions;
    LatLng markerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);

        LatLng currentLocation = null;

        try{
            currentLocation = getCurrentLocation();
        } catch (SecurityException e){
            e.printStackTrace();
        }

        //Si se pudo obtener la ubicacion
        if(currentLocation != null){
            //Movemos la camara para que apunte a otra cordenada diferente a la default
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16.f));
        } else {
            //Si no se pudo obtener la ubicacion
            //Ponemos una ubicacion fija
            LatLng mtyLocation = new LatLng(26.65, -100.29);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mtyLocation, 16.f));
        }

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addMarker("Mi fiesta", latLng, true, false);
            }
        });
    }

    private void addMarker(String tittle, LatLng position, boolean clean, boolean polys) {
        if(clean){
            map.clear();
        }

        //De esta maanera se pueden agregar marcadores al mapa
        MarkerOptions opts = new MarkerOptions();
        opts.position(position);
        opts.title(tittle);
        markerPosition=position;

        Intent returnIntent = new Intent();
        returnIntent.putExtra("latitude",markerPosition.latitude);
        returnIntent.putExtra("longitude",markerPosition.longitude);
        setResult(Activity.RESULT_OK,returnIntent);
        //finish();
        // La clase googleMap tiene el metodo addmaker
        map.addMarker(opts);

        if(!polys)
            return;

        if(markerPositions == null)
            markerPositions = new ArrayList<>();

        //EXTRA: tambien se pueden poner lineas dentro del mapa
        PolylineOptions line = new PolylineOptions();
        line.width(8);
        line.color(Color.BLUE);

        if(markerPositions.size() > 0) {
            LatLng latLng = markerPositions.get(markerPositions.size() - 1);
            line.add(latLng);
        }
        line.add(position);
        markerPositions.add(position);
        //Muestra una linea en el mapa
        map.addPolyline(line);

    }
    private LatLng getCurrentLocation() throws SecurityException {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for(String provider : providers){
            Location l = locationManager.getLastKnownLocation(provider);
            if(l == null) {
                continue;
            }
            if(bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy() ) {
                showToast("Proveedor seleccionado: " + provider);
                bestLocation = l;
            }
        }
        if(bestLocation == null){
            showToast("No se pudo obtener la ubicacion. Espere un momento");
            return null;
        }
        return new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
