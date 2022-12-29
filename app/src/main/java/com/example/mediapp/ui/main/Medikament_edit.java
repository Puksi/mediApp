package com.example.mediapp.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mediapp.MainActivity;
import com.example.mediapp.R;

import java.util.ArrayList;
import java.util.Objects;


public class Medikament_edit extends Medikamente_fragment {

    ArrayList<Medikament> meineMedikamenteListe;
    ArrayList<Medikament> meineMedikamenteListeMorgens;
    ArrayList<Medikament> meineMedikamenteListeMittags;
    ArrayList<Medikament> meineMedikamenteListeAbends;
    MainActivity myActivity;
    int zeile;

    public Medikament_edit() {
    }

    public Medikament_edit(int pos) {
        zeile = pos;
    }

    @SuppressWarnings("unused")
    public static Medikament_edit newInstance(int id) {
        Medikament_edit fragment = new Medikament_edit();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = ((MainActivity) context);
        meineMedikamenteListe = ((MainActivity) context).getMedikamenteListe();
        meineMedikamenteListeMorgens = ((MainActivity) context).getMedikamenteListeMorgens();
        meineMedikamenteListeMittags = ((MainActivity) context).getMedikamenteListeMittags();
        meineMedikamenteListeAbends = ((MainActivity) context).getMedikamenteListeAbends();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medikament_edit, container, false);
        Button save = view.findViewById(R.id.speichern_edit);
        Button delete = view.findViewById(R.id.löschen_edit);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch kalenderSwitch = view.findViewById(R.id.switch1);
        CheckBox morgens = view.findViewById(R.id.morgens);
        CheckBox mittags = view.findViewById(R.id.mittags);
        CheckBox abends = view.findViewById(R.id.abends);
        TextView name = view.findViewById(R.id.medikamentName);
        EditText menge = view.findViewById(R.id.mengeMedikament);
        EditText kommentar = view.findViewById(R.id.kommentar);


        Medikament medikament = meineMedikamenteListe.get(zeile);

        name.setText(medikament.getMedikament_name());
        kalenderSwitch.setChecked(medikament.isImKalender());
        morgens.setChecked(medikament.isEinnahme_frueh());
        mittags.setChecked(medikament.isEinnahme_mittag());
        abends.setChecked(medikament.isEinnahme_abends());
        menge.setText(Integer.toString(medikament.getAnzahl_medikamente()));
        kommentar.setText(medikament.getKommentar());
        if(Objects.equals(medikament.getKommentar(), "") || kommentar.length() == 0){
            kommentar.setHint("Kommentar");
        } else {
            medikament.setKommentar(kommentar.getText().toString()); }

        save.setOnClickListener(view1 -> {
            medikament.setMedikament_name(name.getText().toString());
            medikament.setImKalender(kalenderSwitch.isChecked());
            medikament.setKommentar(kommentar.getText().toString());
            medikament.setEinnahme_frueh(morgens.isChecked());
            medikament.setEinnahme_mittag(mittags.isChecked());
            medikament.setEinnahme_abends(abends.isChecked());

            if (menge.getText().toString().matches("")) {
                menge.setText("1");
                menge.setHint("Bitte eine Menge eingeben");
            } else if (Integer.parseInt(menge.getText().toString()) <= 0) {
                menge.setText("");
                menge.setHint("Bitte eine Anzahl größer als 0 eingeben.");
            }
            else{
                medikament.setAnzahl_medikamente(Integer.parseInt(menge.getText().toString()));
            Medikamente_fragment medikamente_fragment = new Medikamente_fragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(((ViewGroup) requireView().getParent()).getId(), medikamente_fragment, "medikament_fragment")
                    .addToBackStack(null)
                    .commit();}

        });

        delete.setOnClickListener(view12 -> {

            meineMedikamenteListe.remove(medikament);
            if (medikament.isEinnahme_frueh()) {
                meineMedikamenteListeMorgens.remove(medikament);
            }
            if (medikament.isEinnahme_mittag()) {
                meineMedikamenteListeMittags.remove(medikament);
            }
            if (medikament.isEinnahme_abends()) {
                meineMedikamenteListeAbends.remove(medikament);
            }

            Medikamente_fragment medikamente_fragment = new Medikamente_fragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(((ViewGroup) requireView().getParent()).getId(), medikamente_fragment, "medikament_fragment")
                    .addToBackStack(null)
                    .commit();

        });
        
        return view;
    }
}
