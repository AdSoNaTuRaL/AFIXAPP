package com.example.afixapp.relajacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.afixapp.R;
import com.example.afixapp.adapters.RecyclerViewAdapterShowEstrategias;

import java.util.ArrayList;

public class Estrategias extends AppCompatActivity {

    private static final String TAG = "Estrategias";

    private ArrayList<String> mEstrategiaTitulos = new ArrayList<>();
    private ArrayList<String> mEstrategiaDesc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estrategias);

        Log.d(TAG, "onCreate: started");
        initText();
    }

    private void initText(){
        Log.d(TAG, "initText: Preparando textos");

        for(int i = 1; i < 22; i++){
            mEstrategiaTitulos.add(getString(R.string.estrategias) + " " + i);
        }

        mEstrategiaDesc.add(getString(R.string.desc_estrat1));
        mEstrategiaDesc.add(getString(R.string.desc_estrat2));
        mEstrategiaDesc.add(getString(R.string.desc_estrat3));
        mEstrategiaDesc.add(getString(R.string.desc_estrat4));
        mEstrategiaDesc.add(getString(R.string.desc_estrat5));
        mEstrategiaDesc.add(getString(R.string.desc_estrat6));
        mEstrategiaDesc.add(getString(R.string.desc_estrat7));
        mEstrategiaDesc.add(getString(R.string.desc_estrat8));
        mEstrategiaDesc.add(getString(R.string.desc_estrat9));
        mEstrategiaDesc.add(getString(R.string.desc_estrat10));
        mEstrategiaDesc.add(getString(R.string.desc_estrat11));
        mEstrategiaDesc.add(getString(R.string.desc_estrat12));
        mEstrategiaDesc.add(getString(R.string.desc_estrat13));
        mEstrategiaDesc.add(getString(R.string.desc_estrat14));
        mEstrategiaDesc.add(getString(R.string.desc_estrat15));
        mEstrategiaDesc.add(getString(R.string.desc_estrat16));
        mEstrategiaDesc.add(getString(R.string.desc_estrat17));
        mEstrategiaDesc.add(getString(R.string.desc_estrat18));
        mEstrategiaDesc.add(getString(R.string.desc_estrat19));
        mEstrategiaDesc.add(getString(R.string.desc_estrat20));
        mEstrategiaDesc.add(getString(R.string.desc_estrat21));

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recycleview");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapterShowEstrategias adapter = new RecyclerViewAdapterShowEstrategias(this, mEstrategiaTitulos, mEstrategiaDesc);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
