package DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
import java.nio.charset.StandardCharsets;

import Models.Usuario;

/**
 * Created by Ayrton on 09/05/2017.
 */

public class DaoUsuario extends AsyncTask<Object, Integer, Object> {
    static final String SERVER_PATH = "http://shotingstars.tk/PartyApp/index.php";
    static final int TIMEOUT = 1000;

    Context m_context;
    ProgressDialog m_progressDialog;

    public DaoUsuario(Context m_context) {
        this.m_context = m_context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        m_progressDialog = new ProgressDialog(m_context);
        m_progressDialog.setTitle("Conectando");
        m_progressDialog.setMessage("Espere...");
        m_progressDialog.setCancelable(false);
        m_progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object... params) {

            String action = (String) params[0];
            if (action.equals("signup")) {
                Usuario signupUser = signup((Usuario) params[1]);
                // Llamamos a nuestro callback
                NetCallback netCallback = (NetCallback) params[2];
                netCallback.onWorkFinish(signupUser);
            } else if(action.equals("LogIn")){
                Usuario usuario = LogIn((Usuario)params[1]);
                NetCallback netCallback = (NetCallback) params[2];
                netCallback.onWorkFinish(usuario);
            }
            return null;

    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        m_progressDialog.dismiss();
    }
    public Usuario signup(Usuario user) {

        // Parametros que se enviaran al servidor.
        // Es necesario agregar el caracter "&" seguido del nombre de la variable (llave) despues un "=" seguido del valor
        // Es algo basico de Desarrollo Web pero por lo pronto en android veanlo de tal forma que cada variable
        // que se quiera enviar al servidor debe ponerse un &NombreVariable=Valor para despues recuperar esas variables
        // desde el servidor ya sea por medio de POST (PHP: $_POST['nombreVariable']) o GET (PHP: $_GET['nombreVariable'])
        // Dependiendo del header de la peticion (Content-Type) sera o no necesario codificar los valores a algun formato
        // ejemplo UTF-8.


        String postParams = "&AgregarUsuario=true&nombre="+user.getNombre()+"&correo="+user.getCorreo()+"&contrasenia="+user.getContraseña()+"&sexo=Masculino&avatar=qwe&nick=yaJalo";

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
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
           // conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + this.boundary);

            //recomienda enviar el peso de lo que enviaremos.
            conn.setFixedLengthStreamingMode(postParams.getBytes().length);

            //nos da un stream de datos para comenzar a escribir en el lo que escriba se lo envia al servidor.
            OutputStream out= new BufferedOutputStream(conn.getOutputStream());
            out.write(postParams.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            int responseCode=conn.getResponseCode();

            Log.w("RESPONSE CODE"," "+ responseCode);

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String responseString= inputStreamToString(in);

            try {
                JSONObject jsonObject= new JSONObject(responseString);
                int errorCode = jsonObject.getInt("error_code");
                int insertId= jsonObject.getInt("idUsuario");
                String nickname= jsonObject.getString("nick");
                String nombre = jsonObject.getString("nombreUsuario");

                user.setIdUsuario(insertId);
                user.setNickName(nickname);
                user.setNombre(nombre);


            }catch (JSONException e){
                e.printStackTrace();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
    public Usuario LogIn(Usuario usuario)
    {
        String postParams = "&action=signup&userJson="+usuario.toJSON();

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
            conn.setRequestMethod( "POST" );
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
                int errorCode = jsonObject.getInt("error_code");
                int insertId= jsonObject.getInt("id");

                usuario.setIdUsuario(insertId);

            }catch (JSONException e){
                e.printStackTrace();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return usuario;
    }

    // Metodo que lee un String desde un InputStream (Convertimos el InputStream del servidor en un String)
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
