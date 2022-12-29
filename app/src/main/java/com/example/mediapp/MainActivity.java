package com.example.mediapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mediapp.ui.main.Medikament;
import com.example.mediapp.ui.main.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    private ArrayList<Medikament> medikamenteListe = new ArrayList<>();
    private final ArrayList<Medikament> medikamenteHistorie = new ArrayList<>();
    ArrayList<Medikament> medikamenteListeMorgens;
    ArrayList<Medikament> medikamenteListeMittags;
    ArrayList<Medikament> medikamenteListeAbends;

    public ArrayList<Medikament> getMedikamenteListeMorgens() {
        return medikamenteListeMorgens;
    }

    public void setMedikamenteListeMorgens(ArrayList<Medikament> medikamenteListeMorgens) {
        this.medikamenteListeMorgens = medikamenteListeMorgens;
    }

    public ArrayList<Medikament> getMedikamenteListeMittags() {
        return medikamenteListeMittags;
    }

    public void setMedikamenteListeMittags(ArrayList<Medikament> medikamenteListeMittags) {
        this.medikamenteListeMittags = medikamenteListeMittags;
    }

    public ArrayList<Medikament> getMedikamenteListeAbends() {
        return medikamenteListeAbends;
    }

    public void setMedikamenteListeAbends(ArrayList<Medikament> medikamenteListeAbends) {
        this.medikamenteListeAbends = medikamenteListeAbends;
    }

    public ArrayList<Medikament> getMedikamenteHistorie() {
        return medikamenteHistorie;
    }

    public ArrayList<Medikament> getMedikamenteListe() {
        return medikamenteListe;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager.setAdapter(myViewPagerAdapter);
        
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }
        });

        loadMedikamenteListe();
    }

    @Override
    protected void onStop() {

        saveMedikamenteListe();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveMedikamenteListe();
    }

    public void loadMedikamenteListe(){

        SharedPreferences prefs = getSharedPreferences("MeineMedikamenteListe", Context.MODE_PRIVATE);
        String MedikamenteListeJSON = prefs.getString("MeineMedikamenteListe","");

        if (MedikamenteListeJSON == null || MedikamenteListeJSON.length() <= 4) {
            medikamenteListe = new ArrayList<>();
        }else {
            medikamenteListe = new Gson().fromJson(MedikamenteListeJSON, new TypeToken<List<Medikament>>()
            {}.getType());
        }
    }

    public void saveMedikamenteListe() {
        medikamenteListe.size();
        {
            String MedikamentenListeJSON = new Gson().toJson(medikamenteListe);
            SharedPreferences prefs = getSharedPreferences("MeineMedikamenteListe", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("MeineMedikamenteListe", MedikamentenListeJSON);
            editor.apply();
        }
    }

}
