package com.example.mediapp.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.mediapp.MainActivity;
import com.example.mediapp.R;

import java.util.ArrayList;


public class Medikamente_fragment extends Fragment {

    String einname;
    int zeile;
    ArrayList<String> items = new ArrayList<String>();
    ListView listView;
    ArrayAdapter<Medikament> adapter;
    EditText input;
    Button btn;
    ProgressDialog pd;
    MainActivity mainActivity;
    public ArrayList<Medikament> meineMedikamenteListe;

    private MainActivity myContext;

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
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().add()
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myContext = (MainActivity) context;
        meineMedikamenteListe = ((MainActivity) context).getMedikamenteListe();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medikamente_fragment, container, false);
        Button btn = view.findViewById(R.id.buttonMedikamente);
        EditText input = view.findViewById(R.id.editTextNameMedikament);
        ListView liste = view.findViewById(R.id.listviewerMedikamente);

        adapter = new ArrayAdapter<Medikament>(getActivity(), android.R.layout.simple_list_item_1, meineMedikamenteListe);

        liste.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medikament = input.getText().toString();
                ArrayList<Medikament> medikamenteListe = myContext.getMedikamenteListe();
//                medikamenteListe.add(new Medikament(medikament));
//                   ((MainActivity)getActivity()).addMedikament(new Medikament(medikament));
                Medikament medikament2 = new Medikament(medikament, true,
                        false, false, false,
                        1, "" );

                if(medikament.length() == 0){
                    input.setHint("Bitte ein Medikament eingeben");
                }else{
//
                    medikamenteListe.add(medikament2);
                    input.setText("");

                    adapter.notifyDataSetChanged();

                }

            }
        });

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    Medikament_edit medikament_edit = new Medikament_edit(i);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(((ViewGroup)getView().getParent()).getId(), medikament_edit, "fragment_medikament_edit")
                            .addToBackStack(null)
                            .commit();
                    // TODO: 06/12/2022 Listener auch in Medikament_edit und dort die ids anpassen und nicht hier
                    zeile = i;



                }catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();

                }





            }
        });

        return view;
    }

    public int getZeile(){
        return zeile;
    }

}
