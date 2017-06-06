package DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.enriquebecerram.partyapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import Models.Evento;

/**
 * Created by Ayrton on 13/05/2017.
 */

public class DaoEvento extends AsyncTask<Object, Integer, Object> {

    static final String SERVER_PATH = "http://shotingstars.tk/PartyApp/index.php";
    static final int TIMEOUT = 10000;

    Context m_context;
    ProgressDialog m_progressDialog;

    public DaoEvento(Context m_context) {
        this.m_context = m_context;
    }


    @Override
    protected Object doInBackground(Object... objects) {

        String action = (String) objects[0];
        if (action.equals("getEvento")) {
            Evento signupUser = getEvento((Evento) objects[1]);
            // Llamamos a nuestro callback
            NetCallback netCallback = (NetCallback) objects[2];
            netCallback.onWorkFinish(signupUser);
        } else if(action.equals("createEvento")){
            Evento evento =CreateEvento((Evento)objects[1]);
            NetCallback netCallback = (NetCallback) objects[2];
            netCallback.onWorkFinish(evento);
        }else if(action.equals("getEventosPublicos")){
            List<Evento> eventoList = getEventos();
            NetCallback netCallback = (NetCallback)objects[2];
            netCallback.onWorkFinish(eventoList);
        }
        return null;
    }

    public Evento getEvento (Evento evento){

        String postParams = "&action=signup&userJson="+evento.toJSON();
        Evento event= new Evento();
        URL url=null;
        //Controla la informacion con la cual podemos enviar y recivir datos del servidor
        HttpURLConnection conn = null;

        try {
            url=new URL(SERVER_PATH);

            //se establece la conexion con el servidor
            conn =(HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(TIMEOUT);

            //setea el envio y recepcion entre el servidor y tu aplicación
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //el tipo de dato que le vamos a mandar.
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            //recomienda enviar el peso de lo que enviaremos.
            conn.setFixedLengthStreamingMode(postParams.getBytes().length);

            //nos da un stream de datos para comenzar a escribir en el lo que escriba se lo envia al servidor.
            OutputStream out= new BufferedOutputStream(conn.getOutputStream());
            out.write(postParams.getBytes());
            out.flush();
            out.close();
            int responseCode=conn.getResponseCode();

            Log.w("RESPONSE CODE"," "+ responseCode);

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String responseString= inputStreamToString(in);

            try {

                JSONObject jsonObject= new JSONObject(responseString);
                //int errorCode = jsonObject.getInt("error_code");
                int idEvento = jsonObject.getInt("idEvento");
                String nombreEvento= jsonObject.getString("nombreEvento");
                String lugarLa= jsonObject.getString("lugarLa");
                String lugarLo= jsonObject.getString("lugarLo");
                String descripcion= jsonObject.getString("descripcion");
                int privado= jsonObject.getInt("privado");
                String imagenPath= jsonObject.getString("imagenPath");
                int idCreador= jsonObject.getInt("idCreador");


                String fecha = jsonObject.getString("fecha");
                String fechaCreacion = jsonObject.getString("fechaCreacion");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss aa");
                Date fechaDate= new Date();
                Date fechaCreacionDate= new Date();
                try {
                    fechaDate=dateFormat.parse(fecha);
                    fechaCreacionDate= dateFormat.parse(fechaCreacion);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                event = new Evento(idEvento,nombreEvento,lugarLa, lugarLo,descripcion,fechaDate.toString(),fechaCreacionDate.toString(),privado,imagenPath,idCreador);



            }catch (JSONException e){
                e.printStackTrace();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return event;
    }
    public List<Evento> getEventos(){
        String postParams = "&CargarEventosPublicos=true";
        List<Evento> listEvento = new ArrayList<Evento>();

        Evento event= new Evento();
        URL url=null;
        //Controla la informacion con la cual podemos enviar y recivir datos del servidor
        HttpURLConnection conn = null;

        try {
            url=new URL(SERVER_PATH);

            //se establece la conexion con el servidor
            conn =(HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(TIMEOUT);

            //setea el envio y recepcion entre el servidor y tu aplicación
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //el tipo de dato que le vamos a mandar.
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            //recomienda enviar el peso de lo que enviaremos.
            conn.setFixedLengthStreamingMode(postParams.getBytes().length);

            //nos da un stream de datos para comenzar a escribir en el lo que escriba se lo envia al servidor.
            OutputStream out= new BufferedOutputStream(conn.getOutputStream());
            out.write(postParams.getBytes());
            out.flush();
            out.close();
            int responseCode=conn.getResponseCode();

            Log.w("RESPONSE CODE"," "+ responseCode);

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String responseString= inputStreamToString(in);

            try {


                JSONArray jsonArray = new JSONArray(responseString);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject obj = jsonArray.getJSONObject(i); //0 for just retrieving first object you can loop it
                    //int errorCode = obj.getInt("error_code");
                    int idEvento = obj.getInt("idEvento");
                    String nombreEvento= obj.getString("nombreEvento");
                    String lugar = obj.getString("lugar");
                    String lugarLa= obj.getString("latitud");
                    String lugarLo= obj.getString("longitud");
                    String descripcion= obj.getString("descripcion");
                    int privado= obj.getInt("privado");
                    String imagenPath= obj.getString("imagen");
                    int idCreador= obj.getInt("idCreador");


                    String fecha = obj.getString("fecha");
                    String fechaCreacion = obj.getString("fechaCreacion");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    Date fechaDate= new Date();
                    Date fechaCreacionDate= new Date();
                    try {
                        fechaDate=dateFormat.parse(fecha);
                        fechaCreacionDate= dateFormat.parse(fechaCreacion);

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    event = new Evento(idEvento,nombreEvento,lugarLa, lugarLo,descripcion,fechaDate.toString(),fechaCreacionDate.toString(),privado,imagenPath,idCreador);
                    listEvento.add(event);
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return listEvento;
    }
    public Evento CreateEvento(Evento evento){

              String postParams = "&AgregarEvento=true&nombreEvento="+evento.getNombre()+"&lugar="+evento.getLugar()+"&descripcion="+evento.getDescripcion()+
              "&fecha="+evento.getFecha()+"&privado="+evento.getPrivado()+"&idCreador="+evento.getIdCreador()+
              "&longitud="+evento.getLugarLo()+"&latitud="+evento.getLugarLa();

        URL url=null;
        //Controla la informacion con la cual podemos enviar y recivir datos del servidor
        HttpURLConnection conn = null;

        try {
            url=new URL(SERVER_PATH);

            //se establece la conexion con el servidor
            conn =(HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(TIMEOUT);

            //setea el envio y recepcion entre el servidor y tu aplicación
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //el tipo de dato que le vamos a mandar.
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            //recomienda enviar el peso de lo que enviaremos.
            conn.setFixedLengthStreamingMode(postParams.getBytes().length);

            //nos da un stream de datos para comenzar a escribir en el lo que escriba se lo envia al servidor.
            OutputStream out= new BufferedOutputStream(conn.getOutputStream());
            out.write(postParams.getBytes());
            out.flush();
            out.close();
            int responseCode=conn.getResponseCode();

            Log.w("RESPONSE CODE"," "+ responseCode);

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String responseString= inputStreamToString(in);
            JSONObject jsonObject= new JSONObject(responseString);
            Toast.makeText(m_context, jsonObject.toString(), Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return evento;

    }
    private String inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder response = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            while((rLine = rd.readLine()) != null)
            {
                response.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

}
