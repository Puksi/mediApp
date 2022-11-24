package com.example.mediapp.ui.main;

import android.app.ListActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mediapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Medikamente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Medikamente extends ListActivity{

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
//    Button AddItem = findViewById(R.id.buttonMedikamente);
//    EditText Eingabe = findViewById(R.id.editTextNameMedikament);

    private static final String ARG_SECTION_NUMBER = "section_number";



    public Medikamente() {
        // Required empty public constructor
    }


    public static Medikamente newInstance() {
        Medikamente fragment = new Medikamente();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_medikamente);
        EditText eingabe = (EditText) findViewById(R.id.editTextNameMedikament);
        Button button = (Button) findViewById(R.id.buttonMedikamente);
        ListView liste = (ListView) findViewById(R.id.listviewerMedikamente);
        adapter = new ArrayAdapter<String>(this, R.layout.fragment_medikamente, listItems);
        setListAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }





}