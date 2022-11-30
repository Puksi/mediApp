package com.example.mediapp.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mediapp.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Medikament_edit extends Fragment {

    ArrayList<String> items = new ArrayList<String>();

    public Medikament_edit() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public static Medikament_edit newInstance(ArrayList<String> liste) {
        Medikament_edit fragment = new Medikament_edit();
        Bundle args = new Bundle();
        ArrayList<String> items = liste;
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

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

        name.setText("123");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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