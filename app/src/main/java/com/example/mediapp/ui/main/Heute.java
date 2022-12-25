package com.example.mediapp.ui.main;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.mediapp.MainActivity;
import com.example.mediapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class Heute extends Fragment {
    ArrayAdapter<Medikament> adapter2;
    MyArrayAdapter adapterMorgens;
    MyArrayAdapter adapterMittags;
    MyArrayAdapter adapterAbends;
    private MainActivity myActivity;
    public ArrayList<Medikament> meineMedikamenteListe;
    public ArrayList<Medikament> meineMedikamenteListeMorgens;
    ArrayList<Medikament> meineMedikamenteListeMittags;
    ArrayList<Medikament> meineMedikamenteListeAbends;
    Medikament selectedMedicament;
    Medikament medikament;
    boolean musik_gespielt = false;

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


//        if (!meineMedikamenteListeMittags.isEmpty()){
//            meineMedikamenteListeMittags.get(0).setZeitEingenommen(null);}

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
                if (selectedMedicament!=null && (meineMedikamenteListeMorgens.size()!=0 ||
                        meineMedikamenteListeMittags.size()!=0  ||
                        meineMedikamenteListeAbends.size()!=0)){
                    Medikament medikament3 = new Medikament(selectedMedicament.getMedikament_name());
                    SimpleDateFormat zeitformat = new SimpleDateFormat("d. MMM yyyy HH:mm:ss", Locale.GERMANY);
                    medikament3.setZeitEingenommen(zeitformat.format(Calendar.getInstance().getTime()));
                    medikament3.setEingenommen(true);
                    if (meineMedikamenteListeMorgens.contains(selectedMedicament)){
                        selectedMedicament.setZeitEingenommenMorgens(zeitformat.format(Calendar.getInstance().getTime()));
                    }
                    else if (meineMedikamenteListeMittags.contains(selectedMedicament)){
                        selectedMedicament.setZeitEingenommenMittags(zeitformat.format(Calendar.getInstance().getTime()));
                    }
                    else if (meineMedikamenteListeAbends.contains(selectedMedicament)){
                        selectedMedicament.setZeitEingenommenAbends(zeitformat.format(Calendar.getInstance().getTime()));
                    }
//                    selectedMedicament.setZeitEingenommen(zeitformat.format(Calendar.getInstance().getTime()));
                    medikamenteHistorie.add(medikament3);}
            }
        });

        verschieben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedMedicament.isEinnahme_frueh() && !selectedMedicament.isEinnahme_mittag()
                        && !selectedMedicament.isEinnahme_abends()){
//                    meineMedikamenteListeMorgens.remove(selectedMedicament);
//                    meineMedikamenteListeMittags.add(selectedMedicament);
                    selectedMedicament.setEinnahme_frueh(false);
                    selectedMedicament.setEinnahme_mittag(true);
                }
                else if (!selectedMedicament.isEinnahme_frueh() && selectedMedicament.isEinnahme_mittag()
                        && !selectedMedicament.isEinnahme_abends() || selectedMedicament.isEinnahme_frueh()
                        && selectedMedicament.isEinnahme_mittag() & !selectedMedicament.isEinnahme_abends()){
//                    meineMedikamenteListeMittags.remove(selectedMedicament);
//                    meineMedikamenteListeAbends.add(selectedMedicament);
                    selectedMedicament.setEinnahme_mittag(false);
                    selectedMedicament.setEinnahme_abends(true);
                }
                else if (!selectedMedicament.isEinnahme_frueh() && !selectedMedicament.isEinnahme_mittag()
                        && selectedMedicament.isEinnahme_abends() || !selectedMedicament.isEinnahme_frueh()
                        && selectedMedicament.isEinnahme_mittag() && selectedMedicament.isEinnahme_abends()){
//                    meineMedikamenteListeAbends.remove(selectedMedicament);
//                    meineMedikamenteListeMorgens.add(selectedMedicament);
                    selectedMedicament.setEinnahme_abends(false);
                    selectedMedicament.setEinnahme_frueh(true);
                }
                else if (selectedMedicament.isEinnahme_frueh() && !selectedMedicament.isEinnahme_mittag()
                        && selectedMedicament.isEinnahme_abends()){
//                    meineMedikamenteListeAbends.remove(selectedMedicament);
                    selectedMedicament.setEinnahme_abends(false);
                    selectedMedicament.setEinnahme_frueh(true);
                }
                else if (selectedMedicament.isEinnahme_frueh() && selectedMedicament.isEinnahme_mittag()
                        && selectedMedicament.isEinnahme_abends()){
//                    meineMedikamenteListeAbends.remove(selectedMedicament);
                    selectedMedicament.setEinnahme_abends(false);
                    selectedMedicament.setEinnahme_frueh(true);
                }

                erzeugeMorgensListe(meineMedikamenteListe);
                adapterMorgens.notifyDataSetChanged();
                adapterMittags.notifyDataSetChanged();
                adapterAbends.notifyDataSetChanged();
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
        musik_gespielt = false;
        for (int i = 0; i < arrayList.size(); i++) {
            medikament = arrayList.get(i);
            if (medikament.isEinnahme_frueh() && medikament.isImKalender() && !meineMedikamenteListeMorgens.contains(medikament)){
                meineMedikamenteListeMorgens.add(medikament);
                SimpleDateFormat stunden = new SimpleDateFormat("HH", Locale.GERMANY);
                SimpleDateFormat minuten = new SimpleDateFormat("mm", Locale.GERMANY);
                SimpleDateFormat sekunden = new SimpleDateFormat("ss", Locale.GERMANY);
//        if (zeitformat2.format(Calendar.getInstance().getTime()).equals("16:19")) {}
                MediaPlayer music = MediaPlayer.create(this.myActivity, R.raw.notification_sound);
                // Beispiel Uhrzeit 11:45:00
                int time = (12 - Integer.parseInt(stunden.format(Calendar.getInstance().getTime()))) * 3600
                        + (46-Integer.parseInt(minuten.format(Calendar.getInstance().getTime()))) * 60
                        + (-Integer.parseInt(sekunden.format(Calendar.getInstance().getTime())));

                final int milliSecond = (time * 1000);
                new CountDownTimer(milliSecond, 1000) {
                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {
                        if (!meineMedikamenteListeMorgens.isEmpty()){
                            for(int i =0; i < meineMedikamenteListeMorgens.size(); i++){
                            if (meineMedikamenteListeMorgens.get(i).getZeitEingenommenMorgens() == null
                            && !musik_gespielt){
                                music.start();
                                musik_gespielt = true;
                            }
                            }}
                        //meineMedikamenteListeMorgens.get(0).setZeitEingenommen(null);
                    }//1000 is equal to 1 second

                }.start();
            }
            if (medikament.isEinnahme_frueh() && !medikament.isImKalender() && meineMedikamenteListeMorgens.contains(medikament)
                    || !medikament.isEinnahme_frueh() && medikament.isImKalender() && meineMedikamenteListeMorgens.contains(medikament)){
                meineMedikamenteListeMorgens.remove(medikament);
            }
            if (medikament.isEinnahme_mittag() && medikament.isImKalender() && !meineMedikamenteListeMittags.contains(medikament)){
                meineMedikamenteListeMittags.add(medikament);
                SimpleDateFormat stunden = new SimpleDateFormat("HH", Locale.GERMANY);
                SimpleDateFormat minuten = new SimpleDateFormat("mm", Locale.GERMANY);
                SimpleDateFormat sekunden = new SimpleDateFormat("ss", Locale.GERMANY);
//        if (zeitformat2.format(Calendar.getInstance().getTime()).equals("16:19")) {}
                MediaPlayer music = MediaPlayer.create(this.myActivity, R.raw.notification_sound);

                // Beispiel Uhrzeit 13:45:00
                int time = (12 - Integer.parseInt(stunden.format(Calendar.getInstance().getTime()))) * 3600
                        + (45-Integer.parseInt(minuten.format(Calendar.getInstance().getTime()))) * 60
                        + (-Integer.parseInt(sekunden.format(Calendar.getInstance().getTime())));

                final int milliSecond = (time * 1000);
                new CountDownTimer(milliSecond, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        if (!meineMedikamenteListeMittags.isEmpty()){
                            for(int i =0; i < meineMedikamenteListeMittags.size(); i++){
                                if (meineMedikamenteListeMittags.get(i).getZeitEingenommenMittags() == null
                                        && !musik_gespielt){
                                    music.start();
                                    musik_gespielt = true;
                                }
                            }}
                    }//1000 is equal to 1 second

                }.start();
            }
            if (medikament.isEinnahme_mittag() && !medikament.isImKalender() && meineMedikamenteListeMittags.contains(medikament)
                    || !medikament.isEinnahme_mittag() && medikament.isImKalender() && meineMedikamenteListeMittags.contains(medikament)){
                meineMedikamenteListeMittags.remove(medikament);
            }
            if (medikament.isEinnahme_abends() && medikament.isImKalender() && !meineMedikamenteListeAbends.contains(medikament)){
                meineMedikamenteListeAbends.add(medikament);
                SimpleDateFormat stunden = new SimpleDateFormat("HH", Locale.GERMANY);
                SimpleDateFormat minuten = new SimpleDateFormat("mm", Locale.GERMANY);
                SimpleDateFormat sekunden = new SimpleDateFormat("ss", Locale.GERMANY);
//        if (zeitformat2.format(Calendar.getInstance().getTime()).equals("16:19")) {}
                MediaPlayer music = MediaPlayer.create(this.myActivity, R.raw.notification_sound);

                // Beispiel Uhrzeit 19:45:00
                int time = (12 - Integer.parseInt(stunden.format(Calendar.getInstance().getTime()))) * 3600
                        + (46-Integer.parseInt(minuten.format(Calendar.getInstance().getTime()))) * 60
                        + (-Integer.parseInt(sekunden.format(Calendar.getInstance().getTime())));

                final int milliSecond = (time * 1000);
                new CountDownTimer(milliSecond, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        if (!meineMedikamenteListeAbends.isEmpty()){
                            for(int i =0; i < meineMedikamenteListeAbends.size(); i++){
                                if (meineMedikamenteListeAbends.get(i).getZeitEingenommenAbends() == null
                                        && !musik_gespielt){
                                    music.start();
                                    musik_gespielt = true;
                                }
                            }}
                    }//1000 is equal to 1 second

                }.start();
            }
            if (medikament.isEinnahme_abends() && !medikament.isImKalender() && meineMedikamenteListeAbends.contains(medikament)
                    || !medikament.isEinnahme_abends() && medikament.isImKalender() && meineMedikamenteListeAbends.contains(medikament)){
                meineMedikamenteListeAbends.remove(medikament);
            }

        }

        myActivity.setMedikamenteListeMorgens(meineMedikamenteListeMorgens);
        myActivity.setMedikamenteListeMittags(meineMedikamenteListeMittags);
        myActivity.setMedikamenteListeAbends(meineMedikamenteListeAbends);

    }
}
