package com.example.mediapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mediapp.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MyArrayAdapter extends ArrayAdapter<Medikament> {

    public MyArrayAdapter(@NonNull Context context, ArrayList<Medikament> arrayList) {
        super(context,0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Medikament medikament = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_heute, parent, false);
        }

        TextView nameMedikament = (TextView) convertView.findViewById(R.id.name_heute);
        TextView anzahlMedikament = (TextView) convertView.findViewById(R.id.anzahl_heute);
        TextView kommentar = (TextView) convertView.findViewById(R.id.kommentar_heute);

        nameMedikament.setText(medikament.getMedikament_name());
        anzahlMedikament.setText(String.valueOf(medikament.getAnzahl_medikamente()));
        kommentar.setText(medikament.getKommentar());

        return convertView;


    }
}
