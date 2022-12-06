package com.example.mediapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mediapp.databinding.ActivityMainBinding;
import com.example.mediapp.ui.main.Medikament;
import com.example.mediapp.ui.main.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    private ArrayList<Medikament> medikamenteListe = new ArrayList<>();
    int id = 0;
    long longId = 0;


    public ArrayList<Medikament> getMedikamenteListe() {
        return medikamenteListe;
    }

    public void setMedikamenteListe(ArrayList<Medikament> medikamenteListe) {
        this.medikamenteListe = medikamenteListe;
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
                tabLayout.getTabAt(position).select();
            }
        });

        SharedPreferences prefs = getSharedPreferences("MeineMedikamenteListe", Context.MODE_PRIVATE);
        String MedikamenteListeJSON = prefs.getString("MeineMedikamenteListe","");
        if(MedikamenteListeJSON.length() != 4) {
            medikamenteListe = new Gson().fromJson(MedikamenteListeJSON, new TypeToken<List<Medikament>>() {
            }.getType());
        }else {

        }
        if(medikamenteListe == null){
            medikamenteListe = new ArrayList<>();
        }




    }

//    @Override
//    public void onBackPressed() {
//        int fragments = getSupportFragmentManager().getBackStackEntryCount();
//        if (fragments == 1) {
//            finish();
//            return;
//        }
//
//        super.onBackPressed();
//    }

    @Override
    protected void onStop() {

        String MedikamentenListeJSON = new Gson().toJson(medikamenteListe);

        SharedPreferences prefs = getSharedPreferences("MeineMedikamenteListe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("MeineMedikamenteListe", MedikamentenListeJSON);

        editor.apply();

        super.onStop();
    }

    public void changeFragment() {
        viewPager.setCurrentItem(3);
    }

//    public void addMedikament(Medikament medikament){
//        medikamenteListe.add(medikament);
//    }
//
    public void setIds(int i, long l){
        this.id = i;
        this.longId = l;
    }

    public int getIdFromMain(){
        return this.id;
    }
//
//    public long getLIdFromMain(){
//        return this.longId;
//    }
//
//    public Medikament returnMedikament(int i){
//        return medikamenteListe.get(i);
//    }
//
//    public ArrayList<Medikament> returnMedikamentListe() {
//        return medikamenteListe;
//    }
//
//    public void setMedikament(int i, Medikament medikament) {
//        medikamenteListe.add(i, medikament);
//    }

}
