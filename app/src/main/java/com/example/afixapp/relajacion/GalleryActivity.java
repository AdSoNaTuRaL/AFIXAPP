package com.example.afixapp.relajacion;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.afixapp.R;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estrategia_default);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("estrategiaTitulo") && getIntent().
                hasExtra("estrategiaDescricao")){
            String estrategiaTitulo = getIntent().getStringExtra("estrategiaTitulo");
            String estrategiaDescricao = getIntent().getStringExtra("estrategiaDescricao");

            setText(estrategiaTitulo, estrategiaDescricao);
        }
    }

    private void setText(String estrategiaTitulo, String estrategiaDesc){

        TextView titulo = findViewById(R.id.textTituloEstrategia);
        TextView descricao = findViewById(R.id.textEstrategia);
        titulo.setText(estrategiaTitulo);
        descricao.setText(estrategiaDesc);
    }
}
