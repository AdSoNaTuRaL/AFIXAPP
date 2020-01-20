package com.example.afixapp.menu_principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.afixapp.juegos.Aerobic;
import com.example.afixapp.juegos.Estiramento;
import com.example.afixapp.juegos.Fortalecimento;
import com.example.afixapp.R;
import com.example.afixapp.juegos.Relajacion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Juegos extends AppCompatActivity implements View.OnClickListener {

    private BottomNavigationView bottomnav;
    private CardView aerobic_card, relajacion_card, estiramento_card, fortalecimento_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);

        //DEFININDO OS CARTÕES
        aerobic_card = findViewById(R.id.aerobicoCard);
        relajacion_card =  findViewById(R.id.relajacionCard);
        estiramento_card =  findViewById(R.id.estiramentoCard);
        fortalecimento_card = findViewById(R.id.fortalecimentoCard);

        //Adicionando Click Listenner aos cartões
        aerobic_card.setOnClickListener(this);
        relajacion_card.setOnClickListener(this);
        estiramento_card.setOnClickListener(this);
        fortalecimento_card.setOnClickListener(this);

        bottomnav = findViewById(R.id.bottom_navigation);

        //RESETANDO AS SELECAO DE MENUS

        Menu menu = bottomnav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_game:
                        break;

                    case R.id.nav_tarea:
                        Intent intentTarea = new Intent(Juegos.this, Tareas.class);
                        startActivity(intentTarea);
                        break;

                    case R.id.nav_config:
                        Intent intentConfig = new Intent(Juegos.this, Configuracion.class);
                        startActivity(intentConfig);
                        break;
                    case R.id.nav_mapa:
                        Intent intentMapa = new Intent(Juegos.this, Maps.class);
                        startActivity(intentMapa);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.aerobicoCard: i = new Intent(this, Aerobic.class); startActivity(i); break;
            case R.id.fortalecimentoCard: i = new Intent(this, Fortalecimento.class); startActivity(i); break;
            case R.id.relajacionCard: i = new Intent(this, Relajacion.class); startActivity(i); break;
            case R.id.estiramentoCard: i = new Intent(this, Estiramento.class); startActivity(i); break;
            default:break;
        }
    }
}
