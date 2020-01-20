package com.example.afixapp.juegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.afixapp.R;
import com.example.afixapp.adapters.SlidePagerAdapter;
import com.example.afixapp.fragments.PageFragment1;
import com.example.afixapp.fragments.PageFragment2;
import com.example.afixapp.fragments.PageFragment3;
import com.example.afixapp.fragments.PageFragment4;

import java.util.ArrayList;
import java.util.List;

public class Fortalecimento extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortalecimento);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());
        list.add(new PageFragment4());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);

    }
}
