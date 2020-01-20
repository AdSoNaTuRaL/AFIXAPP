package com.example.afixapp.menu_principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.afixapp.R;

public class Informacion extends AppCompatActivity implements View.OnClickListener{


    private CardView fibroCard, afixaCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        //DEFININDO OS CARTÕES
        fibroCard = findViewById(R.id.fibroCard);
        afixaCard = findViewById(R.id.afixaCard);

        //Adicionando Click Listenner aos cartões
        fibroCard.setOnClickListener(this);
        afixaCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.fibroCard:
                Uri urifibro = Uri.parse("https://es.wikipedia.org/wiki/Fibromialgia");
                i = new Intent(Intent.ACTION_VIEW, urifibro);
                startActivity(i);
                break;
            case R.id.afixaCard:
                Uri uriafixa = Uri.parse("https://afixa.org/");
                i = new Intent(Intent.ACTION_VIEW, uriafixa);
                startActivity(i);
                break;
        }

    }
}
