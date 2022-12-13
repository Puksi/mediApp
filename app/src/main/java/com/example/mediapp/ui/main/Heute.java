package com.example.mediapp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.mediapp.MainActivity;
import com.example.mediapp.R;

import java.util.ArrayList;
import java.util.Calendar;


public class Heute extends Fragment {
    ArrayAdapter<Medikament> adapter2;
    MyArrayAdapter adapterMorgens;
    MyArrayAdapter adapterMittags;
    MyArrayAdapter adapterAbends;
    private MainActivity myActivity;
    public ArrayList<Medikament> meineMedikamenteListe;
    ArrayList<Medikament> meineMedikamenteListeMorgens;
    ArrayList<Medikament> meineMedikamenteListeMittags;
    ArrayList<Medikament> meineMedikamenteListeAbends;
    Medikament selectedMedicament;

    ArrayList<Medikament> medikamenteHistorie;


    public Heute() {
        // Required empty public constructor
    }

    public static Heute newInstance() {
        Heute fragment = new Heute();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myActivity = (MainActivity) context;
        meineMedikamenteListe = ((MainActivity) context).getMedikamenteListe();
        medikamenteHistorie = ((MainActivity) context).getMedikamenteHistorie();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heute, container, false);
        ListView listeMorgens = view.findViewById(R.id.listviewHeute);
        ListView listeMittags = view.findViewById(R.id.listviewHeute_mittags);
        ListView listeAbends = view.findViewById(R.id.listviewHeute_abends);
        Button erledigt = view.findViewById(R.id.erledigt_heute);
        Button verschieben = view.findViewById(R.id.verschieben_heute);

//        adapter2 = new ArrayAdapter<Medikament>(getActivity(), android.R.layout.simple_list_item_1, meineMedikamenteListe);
//        liste2.setAdapter(adapter2);
        meineMedikamenteListeMorgens = new ArrayList<>();
        meineMedikamenteListeMittags = new ArrayList<>();
        meineMedikamenteListeAbends = new ArrayList<>();

        erzeugeMorgensListe(meineMedikamenteListe);

        adapterMorgens = new MyArrayAdapter(getActivity(), meineMedikamenteListeMorgens);
        adapterMittags = new MyArrayAdapter(getActivity(), meineMedikamenteListeMittags);
        adapterAbends = new MyArrayAdapter(getActivity(), meineMedikamenteListeAbends);

        listeMorgens.setAdapter(adapterMorgens);
        listeMittags.setAdapter(adapterMittags);
        listeAbends.setAdapter(adapterAbends);

        adapterMorgens.notifyDataSetChanged();
        adapterMittags.notifyDataSetChanged();
        adapterAbends.notifyDataSetChanged();
//        adapter2.notifyDataSetChanged();

        erledigt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedMedicament!=null){
                    Medikament medikament3 = new Medikament(selectedMedicament.getMedikament_name());
                    medikament3.setZeitEingenommen(Calendar.getInstance().getTime());
                medikamenteHistorie.add(medikament3);}
            }
        });

        verschieben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedMedicament!=null) {
                    if (selectedMedicament.isEinnahme_frueh() && !selectedMedicament.isEinnahme_mittag()
                            && !selectedMedicament.isEinnahme_abends()) {
                        meineMedikamenteListeMorgens.remove(selectedMedicament);
                        meineMedikamenteListeMittags.add(selectedMedicament);
                        selectedMedicament.setEinnahme_frueh(false);
                        selectedMedicament.setEinnahme_mittag(true);
                    } else if (!selectedMedicament.isEinnahme_frueh() && selectedMedicament.isEinnahme_mittag()
                            && !selectedMedicament.isEinnahme_abends() || selectedMedicament.isEinnahme_frueh()
                            && selectedMedicament.isEinnahme_mittag() & !selectedMedicament.isEinnahme_abends()) {
                        meineMedikamenteListeMittags.remove(selectedMedicament);
                        meineMedikamenteListeAbends.add(selectedMedicament);
                        selectedMedicament.setEinnahme_mittag(false);
                        selectedMedicament.setEinnahme_abends(true);
                    } else if (!selectedMedicament.isEinnahme_frueh() && !selectedMedicament.isEinnahme_mittag()
                            && selectedMedicament.isEinnahme_abends() || !selectedMedicament.isEinnahme_frueh()
                            && selectedMedicament.isEinnahme_mittag() && selectedMedicament.isEinnahme_abends()) {
                        meineMedikamenteListeAbends.remove(selectedMedicament);
                        meineMedikamenteListeMorgens.add(selectedMedicament);
                        selectedMedicament.setEinnahme_abends(false);
                        selectedMedicament.setEinnahme_frueh(true);
                    } else if (selectedMedicament.isEinnahme_frueh() && !selectedMedicament.isEinnahme_mittag()
                            && selectedMedicament.isEinnahme_abends()) {
                        meineMedikamenteListeAbends.remove(selectedMedicament);
                        selectedMedicament.setEinnahme_abends(false);
                        selectedMedicament.setEinnahme_frueh(true);
                    } else if (selectedMedicament.isEinnahme_frueh() && selectedMedicament.isEinnahme_mittag()
                            && selectedMedicament.isEinnahme_abends()) {
                        meineMedikamenteListeAbends.remove(selectedMedicament);
                        selectedMedicament.setEinnahme_abends(false);
                        selectedMedicament.setEinnahme_frueh(true);
                    }

                    adapterMorgens.notifyDataSetChanged();
                    adapterMittags.notifyDataSetChanged();
                    adapterAbends.notifyDataSetChanged();
                }
            }
        });

        listeMorgens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMedicament = (Medikament) listeMorgens.getItemAtPosition(position);
            }
        });

        listeMittags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMedicament = (Medikament) listeMittags.getItemAtPosition(position);
            }
        });

        listeAbends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMedicament = (Medikament) listeAbends.getItemAtPosition(position);
            }
        });




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        erzeugeMorgensListe(meineMedikamenteListe);
        adapterMorgens.notifyDataSetChanged();
        adapterMittags.notifyDataSetChanged();
        adapterAbends.notifyDataSetChanged();
    }

    public void erzeugeMorgensListe(ArrayList<Medikament> arrayList) {

        for (int i = 0; i < arrayList.size(); i++) {
            Medikament medikament = arrayList.get(i);
            if (medikament.isEinnahme_frueh() && medikament.isImKalender() && !meineMedikamenteListeMorgens.contains(medikament)){
                    meineMedikamenteListeMorgens.add(medikament);
            }
            if (medikament.isEinnahme_frueh() && !medikament.isImKalender() && meineMedikamenteListeMorgens.contains(medikament)
            || !medikament.isEinnahme_frueh() && medikament.isImKalender() && meineMedikamenteListeMorgens.contains(medikament)){
                meineMedikamenteListeMorgens.remove(medikament);
            }
            if (medikament.isEinnahme_mittag() && medikament.isImKalender() && !meineMedikamenteListeMittags.contains(medikament)){
                meineMedikamenteListeMittags.add(medikament);
            }
            if (medikament.isEinnahme_mittag() && !medikament.isImKalender() && meineMedikamenteListeMittags.contains(medikament) 
                    || !medikament.isEinnahme_mittag() && medikament.isImKalender() && meineMedikamenteListeMittags.contains(medikament)){
                    meineMedikamenteListeMittags.remove(medikament);
            }
            if (medikament.isEinnahme_abends() && medikament.isImKalender() && !meineMedikamenteListeAbends.contains(medikament)){
                meineMedikamenteListeAbends.add(medikament);
            }
            if (medikament.isEinnahme_abends() && !medikament.isImKalender() && meineMedikamenteListeAbends.contains(medikament)
                    || !medikament.isEinnahme_abends() && medikament.isImKalender() && meineMedikamenteListeAbends.contains(medikament)){
                meineMedikamenteListeAbends.remove(medikament);
            }

        }
    }
}
