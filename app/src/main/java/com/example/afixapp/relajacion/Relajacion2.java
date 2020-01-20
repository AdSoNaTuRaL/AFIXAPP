package com.example.afixapp.relajacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.afixapp.R;
import com.example.afixapp.relajacion.Bosque;
import com.example.afixapp.relajacion.Campinas;
import com.example.afixapp.relajacion.Lago;
import com.example.afixapp.relajacion.Montanha;
import com.example.afixapp.relajacion.Oceano;
import com.example.afixapp.relajacion.Praia;

public class Relajacion2 extends AppCompatActivity implements View.OnClickListener {

    private CardView praia, bosque, oceano, lago, montanha, campinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relajacion2);


        //DEFININDO OS CARTÕES
        praia = findViewById(R.id.praia);
        bosque =  findViewById(R.id.bosque);
        oceano =  findViewById(R.id.oceano);
        lago =  findViewById(R.id.lago);
        montanha =  findViewById(R.id.montanha);
        campinas =  findViewById(R.id.campina);

        //Adicionando Click Listenner aos cartões
        praia.setOnClickListener(this);
        bosque.setOnClickListener(this);
        oceano.setOnClickListener(this);
        lago.setOnClickListener(this);
        montanha.setOnClickListener(this);
        campinas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.praia: i = new Intent(this, Praia.class); startActivity(i); break;
            case R.id.bosque: i = new Intent(this, Bosque.class); startActivity(i); break;
            case R.id.oceano: i = new Intent(this, Oceano.class); startActivity(i); break;
            case R.id.lago: i = new Intent(this, Lago.class); startActivity(i); break;
            case R.id.montanha: i = new Intent(this, Montanha.class); startActivity(i); break;
            case R.id.campina: i = new Intent(this, Campinas.class); startActivity(i); break;
            default:break;
        }
    }
}
