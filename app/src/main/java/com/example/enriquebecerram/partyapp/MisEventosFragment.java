package com.example.enriquebecerram.partyapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MisEventosFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_miseventos, container, false);

        Button btnVer = (Button)v.findViewById(R.id.btnNuevoEvento);

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NuevoEvento.class);
                //Intent intent = new Intent(getApplicationContext(), Login.class);
                //Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
