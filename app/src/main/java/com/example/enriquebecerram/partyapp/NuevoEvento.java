package com.example.enriquebecerram.partyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.drive.internal.StringListResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

import Models.Evento;
import database.UsuarioData;

public class NuevoEvento extends AppCompatActivity {

    private MapView mapView;
    Button btnMap;
    LatLng markerPosition;
    Button btnCrearEvento;
    EditText txtNombre;
    EditText txtLugar;
    EditText txtDescripcion;
    DatePicker datePicker;
    Date fecha;
    int EventoPublico;
    double latitude;
    double longitude;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        latitude =data.getDoubleExtra("latitude",0.0);
        longitude =data.getDoubleExtra("longitude",0.0);
        //Toast.makeText(NuevoEvento.this, latitude+"  "+longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_evento);

        btnMap = (Button) findViewById(R.id.btnMapa);
        btnCrearEvento=(Button) findViewById(R.id.btnCrearEvento);
        txtDescripcion=(EditText) findViewById(R.id.editDescripcion);
        txtLugar=(EditText) findViewById(R.id.editLugar);
        txtNombre=(EditText) findViewById(R.id.editNombre);
        datePicker=(DatePicker) findViewById(R.id.datePicker);


        btnMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent InvokePrincipal = new Intent(NuevoEvento.this, MapsActivity.class);
                startActivityForResult(InvokePrincipal,1);

            }
        });

        btnCrearEvento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Evento evento = new Evento();
                evento.setNombre(txtNombre.getText().toString());
                evento.setDescripcion(txtDescripcion.getText().toString());
                evento.setLugar(txtLugar.getText().toString());
                evento.setLugarLa(String.valueOf( latitude));
                evento.setLugarLo(String.valueOf( longitude));
                evento.setFecha(datePicker.toString());
                evento.setIdCreador(new UsuarioData(NuevoEvento.this).getContact(1).getIdUsuario());
            }
        });

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
       /* mapView = (MapView) findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(-2.0051575, 17.7654223))
                        .title("Marker"));

            }
        });*/
    }
}
