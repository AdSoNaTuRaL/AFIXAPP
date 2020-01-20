package com.example.afixapp.menu_principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.afixapp.R;
import com.example.afixapp.juegos.Aerobic;
import com.example.afixapp.juegos.Estiramento;
import com.example.afixapp.juegos.Fortalecimento;
import com.example.afixapp.juegos.Relajacion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{

    private BottomNavigationView bottomnav;

    private CardView perfilCard, infoCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        bottomnav = findViewById(R.id.bottom_navigation);

        //DEFININDO OS CARTÕES
        perfilCard = findViewById(R.id.perfilCard);
        infoCard =  findViewById(R.id.infoCard);

        //Adicionando Click Listenner aos cartões
        perfilCard.setOnClickListener(this);
        infoCard.setOnClickListener(this);

        //RESETANDO AS SELECAO DE MENUS
        Menu menu = bottomnav.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_game:
                        Intent intentJuego = new Intent(Configuracion.this, Juegos.class);
                        startActivity(intentJuego);
                        break;

                    case R.id.nav_tarea:
                        Intent intentTarea = new Intent(Configuracion.this, Tareas.class);
                        startActivity(intentTarea);
                        break;

                    case R.id.nav_config:
                        break;
                    case R.id.nav_mapa:
                        Intent intentMapa = new Intent(Configuracion.this, Maps.class);
                        startActivity(intentMapa);
                        break;
                }
                return false;
            }
        });
    }

    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.perfilCard: i = new Intent(this, Profile.class); startActivity(i); break;
            case R.id.infoCard: i = new Intent(this, Informacion.class); startActivity(i); break;
            default:break;
        }
    }
}
