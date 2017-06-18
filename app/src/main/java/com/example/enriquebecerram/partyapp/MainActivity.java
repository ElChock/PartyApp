package com.example.enriquebecerram.partyapp;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Models.Evento;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private GestureDetectorCompat gestureDetector;



    Button btnCarita;
    Evento evento;
    Button btnDetalleRechazar;
    TextView txtDetalleNombre;
    TextView txtDetalleFecha;
    TextView txtDetallePrivado;
    TextView txtDetalleDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);
        btnDetalleRechazar = (Button)findViewById(R.id.btnDetalleRechazar);
        txtDetalleNombre = (TextView)findViewById(R.id.txtDetalleNombre);
        txtDetalleFecha = (TextView)findViewById(R.id.txtDetalleFecha);
        txtDetallePrivado = (TextView)findViewById(R.id.txtDetallePrivado);
        txtDetalleDescripcion = (TextView)findViewById(R.id.txtDetalleDescripcion);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String eventoString =b.getString("evento");
        try {
            evento= new Evento();
            JSONObject jsonObject= new JSONObject(eventoString);
            int idEvento = jsonObject.getInt("idEvento");
            String nombreEvento= jsonObject.getString("nombre");
            //String lugar = jsonObject.getString("lugar");
            String lugarLa= jsonObject.getString("lugarLa");
            String lugarLo= jsonObject.getString("lugarLo");
            String descripcion= jsonObject.getString("descripcion");
            int privado= jsonObject.getInt("privado");
            //String imagenPath= jsonObject.getString("imagen");
            String imagenPath="";
            int idCreador= jsonObject.getInt("idCreador");

            String fecha = jsonObject.getString("fecha");
            String fechaCreacion = jsonObject.getString("fechaCreacion");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            Date fechaDate= new Date();
            Date fechaCreacionDate= new Date();
            /*try {
                //fechaDate=dateFormat.parse(fecha);
                //fechaCreacionDate= dateFormat.parse(fechaCreacion);

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/

            evento = new Evento(idEvento,nombreEvento,lugarLa, lugarLo,descripcion,fechaDate.toString(),fechaCreacionDate.toString(),privado,imagenPath,idCreador);
        }catch (JSONException e){
            e.printStackTrace();
        }

        txtDetalleDescripcion.setText(evento.getDescripcion());
        txtDetalleNombre.setText(evento.getNombre());
        txtDetalleFecha.setText(evento.getFecha());
        if(evento.getPrivado()==1){
            txtDetallePrivado.setText("Privado");
        }else{
            txtDetallePrivado.setText("Publico");
        }






        //btnCarita = (Button)findViewById(R.id.btnCarita);

        //btnCarita.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View v) {

         //       Intent InvokeLogin = new Intent(MainActivity.this, Login.class);
           //     startActivity(InvokeLogin);

        //    }
        //});
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //txtDetalleFecha.setText("ondown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //txtDetalleFecha.setText("onsingle");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //txtDetalleFecha.setText("onscroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        txtDetalleFecha.setText("onlongpress");
        Toast.makeText(this, "deja de picarele :)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
