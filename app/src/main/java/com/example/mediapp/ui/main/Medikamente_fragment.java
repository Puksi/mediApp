package com.example.mediapp.ui.main;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediapp.R;
import com.example.mediapp.ui.main.placeholder.PlaceholderContent;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class Medikamente_fragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    ArrayList<String> items = new ArrayList<String>();
    ListView listView;
    ArrayAdapter<String> adapter;
    EditText input;
    Button btn;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Medikamente_fragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Medikamente_fragment newInstance(int columnCount) {
        Medikamente_fragment fragment = new Medikamente_fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        super(R.layout.fragment_medikamente_fragment);
//
//        Button btn = findViewById(R.id.buttonMedikamente);
//        EditText input = view.findViewById(R.id.editTextNameMedikament);
//        ListView liste = view.findViewById(R.id.listviewerMedikamente);
//
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//
//        liste.setAdapter(adapter);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String medikament = input.getText().toString();
//                if(medikament == null || medikament.length() == 0){
//                    input.setText("Bitte ein Medikament eingeben");
//                }else{
//                    items.add(medikament);
//                    input.setText("");
//
//                    adapter.notifyDataSetChanged();
//
//                }
//            }
//        });
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medikamente_fragment, container, false);
        Button btn = view.findViewById(R.id.buttonMedikamente);
        EditText input = view.findViewById(R.id.editTextNameMedikament);
        ListView liste = view.findViewById(R.id.listviewerMedikamente);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);

        liste.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medikament = input.getText().toString();
                if(medikament == null || medikament.length() == 0){
                    input.setText("Bitte ein Medikament eingeben");
                }else{
                    items.add(medikament);
                    input.setText("");

                    adapter.notifyDataSetChanged();

                }
            }
        });

        return view;
    }
}