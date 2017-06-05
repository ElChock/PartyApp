package com.example.enriquebecerram.partyapp;


import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import DAO.DaoUsuario;
import DAO.NetCallback;
import Models.Usuario;

/**
 * Created by Ayrton on 09/05/2017.
 */

public class Registrar extends AppCompatActivity {

    String genero;
    TextView txtNombre;
    TextView txtNickname;
    TextView txtCorreo;
    TextView txtContraseña;
    TextView txtContraseña2;
    Button btnRegistrar;
    Button btnAlbum;
    ImageView imageView;
    Bitmap bmp;
    Uri imageUri;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                bmp = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //Toast.makeText(PostImage.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            //Toast.makeText(PostImage.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtNombre = (TextView) findViewById(R.id.txtUsuarioRegistro);
        txtCorreo = (TextView) findViewById(R.id.txtCorreoRegistro);
        txtContraseña = (TextView) findViewById(R.id.txtContraseñaRegistro);
        txtContraseña2 = (TextView) findViewById(R.id.txtContraseñaRegistro2);
        btnRegistrar =(Button) findViewById(R.id.btnRegistrar);
        btnAlbum=(Button) findViewById(R.id.btnImagen);
        imageView=(ImageView) findViewById(R.id.image);
        txtNickname=(TextView) findViewById(R.id.txtNicknameRegistro);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(txtContraseña.getText().toString()==txtContraseña2.getText().toString()){

                Usuario usuario = new Usuario(1,txtNombre.getText().toString(),txtNickname.getText().toString(),txtCorreo.getText().toString(),txtContraseña.getText().toString(),genero,txtNickname.getText().toString(),null);
                DaoUsuario daoUsuario= new DaoUsuario(Registrar.this);
                daoUsuario.execute("signup", usuario, new NetCallback() {
                    @Override
                    public void onWorkFinish(Object data) {
                        final Usuario signUPUser= (Usuario) data;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(signUPUser.getIdUsuario()!=0){
                                    Intent InvokePrincipal = new Intent(Registrar.this, Principal.class);
                                    startActivity(InvokePrincipal);
                                }else {
                                    Toast.makeText(getApplicationContext(),"Error al crear Usuario", Toast.LENGTH_LONG).show();
                                }
                                //Toast.makeText(getApplicationContext(), signUPUser.toJSON(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }

            }
        });

        btnAlbum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });



    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdbFemenino:
                if (checked)
                    genero="Femenino";
                    // Pirates are the best
                    break;
            case R.id.rdbMasculino:
                if (checked)
                    genero="Masculino";
                    // Ninjas rule
                    break;
        }
    }
    private void getImageFromAlbum(){
        try{
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 1);
        }catch(Exception exp){
            Log.i("Error",exp.toString());

        }
    }

}
