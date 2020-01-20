package com.example.afixapp.juegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.afixapp.R;
import com.example.afixapp.adapters.SlidePagerAdapter;
import com.example.afixapp.fragments.PagerFragmentEstiramento1;
import com.example.afixapp.fragments.PagerFragmentEstiramento2;
import com.example.afixapp.fragments.PagerFragmentEstiramento3;
import com.example.afixapp.fragments.PagerFragmentEstiramento4;
import com.example.afixapp.fragments.PagerFragmentEstiramento5;

import java.util.ArrayList;
import java.util.List;

public class Estiramento extends AppCompatActivity {

    private ViewPager pager_estiramento;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estiramento);

        List<Fragment> list = new ArrayList<>();
        list.add(new PagerFragmentEstiramento1());
        list.add(new PagerFragmentEstiramento2());
        list.add(new PagerFragmentEstiramento3());
        list.add(new PagerFragmentEstiramento4());
        list.add(new PagerFragmentEstiramento5());


        pager_estiramento = findViewById(R.id.pager_estiramento);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

        pager_estiramento.setAdapter(pagerAdapter);
    }
}
