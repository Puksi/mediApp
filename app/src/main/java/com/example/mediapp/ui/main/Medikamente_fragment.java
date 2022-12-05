package com.example.mediapp.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediapp.MainActivity;
import com.example.mediapp.R;
import com.example.mediapp.ui.main.placeholder.PlaceholderContent;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;


public class Medikamente_fragment extends Fragment {


    private int mColumnCount = 1;
    ArrayList<String> items = new ArrayList<String>();
    ListView listView;
    ArrayAdapter<String> adapter;
    EditText input;
    Button btn;
    ProgressDialog pd;
    MainActivity mainActivity;


    public Medikamente_fragment() {
    }


    @SuppressWarnings("unused")
    public static Medikamente_fragment newInstance(int columnCount) {
        Medikamente_fragment fragment = new Medikamente_fragment();
        Bundle args = new Bundle();
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
        ArrayList<Medikament> liste_medikament = mainActivity.getMedikamenteListe();



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

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    Medikament_edit medikament_edit = new Medikament_edit();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(((ViewGroup)getView().getParent()).getId(), medikament_edit, "findThisFragment")
                            .addToBackStack(null)
                            .commit();


                }catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();

                }





            }
        });

        return view;
    }


}