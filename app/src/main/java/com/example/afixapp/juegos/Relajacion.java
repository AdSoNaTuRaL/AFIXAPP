package com.example.afixapp.juegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.afixapp.relajacion.Estrategias;
import com.example.afixapp.R;
import com.example.afixapp.relajacion.Relajacion2;

public class Relajacion extends AppCompatActivity implements View.OnClickListener{


    private CardView relajacion_card2, estrategias_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relajacion);

        //DEFININDO OS CARTÕES
        relajacion_card2 = findViewById(R.id.relajacionCard2);
        estrategias_card =  findViewById(R.id.estrategiasCard);

        //Adicionando Click Listenner aos cartões
        relajacion_card2.setOnClickListener(this);
        estrategias_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.relajacionCard2: i = new Intent(this, Relajacion2.class); startActivity(i); break;
            case R.id.estrategiasCard: i = new Intent(this, Estrategias.class); startActivity(i); break;
            default:break;
        }
    }
}
