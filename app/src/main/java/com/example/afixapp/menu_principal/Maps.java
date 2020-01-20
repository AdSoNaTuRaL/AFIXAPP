package com.example.afixapp.menu_principal;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.afixapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private BottomNavigationView bottomnav;
    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        bottomnav = findViewById(R.id.bottom_navigation);
        searchView = findViewById(R.id.sv_location);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(Maps.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);


        //RESETANDO AS SELECAO DE MENUS
        Menu menu = bottomnav.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        /*SWITCH CASE PARA ELEGER QUAL MENU O USUARIO CLICA, E ATRAVES DAS INTENTS REDIRECIONAR PARA
        A INTENT CORRETA*/
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_game:
                        Intent intentJuego = new Intent(Maps.this, Juegos.class);
                        startActivity(intentJuego);
                        break;

                    case R.id.nav_tarea:
                        Intent intentTarea = new Intent(Maps.this, Tareas.class);
                        startActivity(intentTarea);
                        break;

                    case R.id.nav_config:
                        Intent intentConfig = new Intent(Maps.this, Configuracion.class);
                        startActivity(intentConfig);
                        break;
                    case R.id.nav_mapa:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

    }
}
