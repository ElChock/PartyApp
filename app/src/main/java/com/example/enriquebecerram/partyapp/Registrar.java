package com.example.enriquebecerram.partyapp;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import DAO.DaoUsuario;
import DAO.NetCallback;
import Models.Usuario;

/**
 * Created by Ayrton on 09/05/2017.
 */

public class Registrar extends AppCompatActivity {

    TextView txtNickname;
    TextView txtCorreo;
    TextView txtContraseña;
    Button btnRegistrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtNickname = (TextView) findViewById(R.id.txtUsuarioRegistro);
        txtCorreo = (TextView) findViewById(R.id.txtCorreoRegistro);
        txtContraseña = (TextView) findViewById(R.id.txtContraseñaRegistro);
        btnRegistrar =(Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Usuario usuario = new Usuario(txtNickname.getText().toString(),txtCorreo.getText().toString(),txtContraseña.getText().toString());
                DaoUsuario daoUsuario= new DaoUsuario(Registrar.this);
                daoUsuario.execute("signup", usuario, new NetCallback() {
                    @Override
                    public void onWorkFinish(Object data) {
                        final Usuario signUPUser= (Usuario) data;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /*if (signUPUser.getId()!=0){
                                    showToast("Cuenta Creada");
                                    Intent intent= new Intent(Registrar.this, ImageActivity.class);
                                    startActivity(intent);
                                }else{
                                    showToast("Usuario ya Existe");
                                }*/

                                Toast.makeText(getApplicationContext(), signUPUser.toJSON(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });


    }
}
