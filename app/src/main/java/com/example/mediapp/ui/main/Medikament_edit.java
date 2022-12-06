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

import androidx.fragment.app.Fragment;

import com.example.mediapp.MainActivity;
import com.example.mediapp.R;

import java.util.ArrayList;


public class Medikament_edit extends Fragment {

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
        morgens.setChecked(medikament.isEinnahme_frueh());
        mittags.setChecked(medikament.isEinnahme_mittag());
        abends.setChecked(medikament.isEinnahme_abends());
        menge.setText(Integer.toString(medikament.getAnzahl_medikamente()));
        kommentar.setText(medikament.getKommentar());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medikament.setMedikament_name(name.getText().toString());
                medikament.setImKalender(kalenderSwitch.isChecked());
                medikament.setKommentar(kommentar.getText().toString());
                medikament.setEinnahme_frueh(morgens.isChecked());
                medikament.setEinnahme_mittag(mittags.isChecked());
                medikament.setEinnahme_abends(abends.isChecked());
                // TODO: 06/12/2022 medikament muss die Menge der Medikamente übernehmen. VLt den Text view ändern dass der nur int anzeigt


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