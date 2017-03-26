package com.example.enriquebecerram.partyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCarita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnCarita = (Button)findViewById(R.id.btnCarita);

        //btnCarita.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View v) {

         //       Intent InvokeLogin = new Intent(MainActivity.this, Login.class);
           //     startActivity(InvokeLogin);

        //    }
        //});
    }
}
