package com.example.lolo.recuperacionmanuelmorillamanzano;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mapa;
    SupportMapFragment fragmentMapa;
    Localidad l;
    double latitud;
    double longitud;
    LatLng posicion;
    MarkerOptions marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        fragmentMapa= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragMapa);
        fragmentMapa.getMapAsync(this);

        //recoojo localidad mandado del main activity
        l = (Localidad) getIntent().getExtras().getSerializable("objeto");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa=googleMap;
        mapa.setMapType(googleMap.MAP_TYPE_NORMAL);
        mapa.getUiSettings().setZoomControlsEnabled(true);


        latitud=Double.parseDouble(l.getLatitud());
        longitud=Double.parseDouble(l.getLongitud());


        posicion=new LatLng(latitud, longitud);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 2));

        String info="Nombre: "+l.getNombre()+" Codigo: "+l.getCodigo();

        marcador=new MarkerOptions().title(l.getNombre()).position(posicion).snippet(info);

        mapa.addMarker(marcador);






    }
}
