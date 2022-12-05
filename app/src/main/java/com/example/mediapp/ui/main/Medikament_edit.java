package com.example.mediapp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mediapp.R;

import java.util.ArrayList;


public class Medikament_edit extends Fragment {

    ArrayList<String> items = new ArrayList<String>();

    private MyListener listener;

    public Medikament_edit() {
        // Required empty public constructor
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
        listener = (MyListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medikament_edit, container, false);
        Button save = view.findViewById(R.id.speichern_edit);
        Switch kalenderSwitch = view.findViewById(R.id.switch1);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        TextView name = view.findViewById(R.id.medikamentName);
        EditText menge = view.findViewById(R.id.mengeMedikament);
        EditText kommentar = view.findViewById(R.id.kommentar);

        Medikament medikament = listener.returnMedikament(listener.getIdFromMain());

        name.setText(medikament.getMedikament_name());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medikament.setMedikament_name(name.getText().toString());
                medikament.setImKalender(kalenderSwitch.isActivated());
                medikament.setKommentar(kommentar.getText().toString());


                Medikamente_fragment medikamente_fragment = new Medikamente_fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), medikamente_fragment, "findthisFragment2")
                        .addToBackStack(null)
                        .commit();

            }
        });


        return view;
    }
}