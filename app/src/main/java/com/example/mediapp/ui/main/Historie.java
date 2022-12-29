package com.example.mediapp.ui.main;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.mediapp.MainActivity;
import com.example.mediapp.R;
import java.util.ArrayList;


public class Historie extends Fragment {
    MainActivity myActivity;
    ArrayList<Medikament> medikamenteHistorie;
    ArrayAdapter<Medikament> adapter;

    public Historie() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (MainActivity) context;
        medikamenteHistorie = ((MainActivity) context).getMedikamenteHistorie();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historie, container, false);
        ListView listeHistorie = view.findViewById(R.id.listview_historie);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, medikamenteHistorie);
        listeHistorie.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
