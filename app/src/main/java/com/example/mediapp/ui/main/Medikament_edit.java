package com.example.mediapp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mediapp.MainActivity;
import com.example.mediapp.R;

import java.util.ArrayList;


public class Medikament_edit extends Medikamente_fragment {

    ArrayList<Medikament> meineMedikamenteListe;
    MainActivity myActivity;
    int zeile;
    ListView list;

    public Medikament_edit() {
        // Required empty public constructor
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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myActivity = ((MainActivity) context);
        meineMedikamenteListe = ((MainActivity) context).getMedikamenteListe();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medikament_edit, container, false);
        Button save = view.findViewById(R.id.speichern_edit);
        Button delete = view.findViewById(R.id.löschen_edit);
        Switch kalenderSwitch = view.findViewById(R.id.switch1);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        CheckBox morgens = view.findViewById(R.id.morgens);
        CheckBox mittags = view.findViewById(R.id.mittags);
        CheckBox abends = view.findViewById(R.id.abends);
        TextView name = view.findViewById(R.id.medikamentName);
        EditText menge = view.findViewById(R.id.mengeMedikament);
        EditText kommentar = view.findViewById(R.id.kommentar);


        Medikament medikament = meineMedikamenteListe.get(zeile);

        name.setText(medikament.getMedikament_name());
        kalenderSwitch.setChecked(medikament.isImKalender());
//        mittags.setChecked(true);
        morgens.setChecked(medikament.isEinnahme_frueh());
        mittags.setChecked(medikament.isEinnahme_mittag());
        abends.setChecked(medikament.isEinnahme_abends());
        menge.setText(Integer.toString(medikament.getAnzahl_medikamente()));
        kommentar.setText(medikament.getKommentar());
        if(medikament.getKommentar() == "" || kommentar.length() == 0){
            kommentar.setHint("Kommentar");
        } else {
            medikament.setKommentar(kommentar.getText().toString()); }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medikament.setMedikament_name(name.getText().toString());
                medikament.setImKalender(kalenderSwitch.isChecked());
                medikament.setKommentar(kommentar.getText().toString());
                medikament.setEinnahme_frueh(morgens.isChecked());
                medikament.setEinnahme_mittag(mittags.isChecked());
                medikament.setEinnahme_abends(abends.isChecked());
                medikament.setAnzahl_medikamente(Integer.parseInt(menge.getText().toString()));

                medikament.setAnzahl_medikamente(Integer.parseInt(menge.getText().toString()));
//

                if (Integer.parseInt(menge.getText().toString()) <= 0){
                    menge.setText("");
                    menge.setHint("Bitte eine Anzahl größer als 0 eingeben.");
                }
                else if (menge.getText().toString() == "null") {
                    menge.setText("1");
                    menge.setHint("Bitte eine Menge eingeben");
                }
                else{
                Medikamente_fragment medikamente_fragment = new Medikamente_fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), medikamente_fragment, "medikament_fragment")
                        .addToBackStack(null)
                        .commit();}

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                meineMedikamenteListe.remove(medikament);

//                adapter.notifyDataSetChanged();


                Medikamente_fragment medikamente_fragment = new Medikamente_fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), medikamente_fragment, "medikament_fragment")
                        .addToBackStack(null)
                        .commit();

            }
        });


        return view;
    }
}
