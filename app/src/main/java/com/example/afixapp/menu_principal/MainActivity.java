package com.example.afixapp.menu_principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.afixapp.R;
import com.example.afixapp.menu_principal.Configuracion;
import com.example.afixapp.menu_principal.Juegos;
import com.example.afixapp.menu_principal.Maps;
import com.example.afixapp.menu_principal.Tareas;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private CardView maps_card, tareas_card, juegos_card, config_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definindo os cartoes
        maps_card = (CardView) findViewById(R.id.maps_card);
        tareas_card = (CardView) findViewById(R.id.tareas_card);
        juegos_card = (CardView) findViewById(R.id.juegos_card);
        config_card = (CardView) findViewById(R.id.config_card);

        //Adicionando Click Listenner aos cartões
        maps_card.setOnClickListener(this);
        tareas_card.setOnClickListener(this);
        juegos_card.setOnClickListener(this);
        config_card.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.maps_card: i = new Intent(this, Maps.class); startActivity(i); break;
            case R.id.tareas_card: i = new Intent(this, Tareas.class); startActivity(i); break;
            case R.id.juegos_card: i = new Intent(this, Juegos.class); startActivity(i); break;
            case R.id.config_card: i = new Intent(this, Configuracion.class); startActivity(i); break;
            default:break;
        }
    }
}
