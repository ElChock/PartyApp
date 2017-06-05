package com.example.enriquebecerram.partyapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import DAO.DaoUsuario;
import DAO.NetCallback;
import Models.Usuario;
import Networking.VolleyS;
import database.UsuarioData;

public class Login extends AppCompatActivity {

    Button btnLogin;
    Button btnregistrar;
    TextView txtContraseña;
    TextView txtCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnregistrar=(Button) findViewById(R.id.btnRegistroPrincipal);
        txtContraseña=(TextView) findViewById(R.id.txtContrasenia);
        txtCorreo=(TextView) findViewById(R.id.txtUsuario);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario(txtContraseña.getText().toString(),txtCorreo.getText().toString());
                DaoUsuario daoUsuario = new DaoUsuario(Login.this);
                daoUsuario.execute("LogIn",usuario,new NetCallback() {
                    @Override
                    public void onWorkFinish(Object data) {
                        final Usuario signUPUser= (Usuario) data;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(signUPUser.getIdUsuario()!=0){

                                    new UsuarioData(Login.this).insertConact(signUPUser);
                                    Toast.makeText(getApplicationContext(),new UsuarioData(Login.this).getContact(signUPUser.getIdUsuario()).getNombre(), Toast.LENGTH_LONG).show();

                                    Intent InvokePrincipal = new Intent(Login.this, Principal.class);
                                    startActivity(InvokePrincipal);
                                }else {
                                    Toast.makeText(getApplicationContext(),"Error al Iniciar sesion", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });



            }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent InvokePrincipal = new Intent(Login.this, Registrar.class);
                startActivity(InvokePrincipal);
            }
        });
    }

}
