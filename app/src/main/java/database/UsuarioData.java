package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Models.Usuario;

/**
 * Created by Ayrton on 04/06/2017.
 */

public class UsuarioData extends SQLHelper  {
    private static final String sTableName="contact";

    private static final String sColumnID="idUsuario";
    private static final String sColumnNickName="nickName";
    private static final String sColumnName="nombre";
    private static final String sColumnCorreo="correo";
    private static final String sColumnContraseña="contraseña";
    private static final String sColumnGenero="genero";
    private static final String sColumnAvatar="avatar";




    public UsuarioData(Context context) {
        super(context);
    }

    public void insertConact(Usuario contact){
        //abrir la conexion con la base de datos
        SQLiteDatabase db=getWritableDatabase();

        //metodo para guardar informacion
        ContentValues values= new ContentValues();
        values.put(sColumnName,contact.getNombre());
        values.put(sColumnID,contact.getIdUsuario());
        values.put(sColumnNickName,contact.getNickName());
        values.put(sColumnCorreo,contact.getCorreo());
        values.put(sColumnContraseña,contact.getContraseña());
        values.put(sColumnGenero,contact.getGenero());
        values.put(sColumnAvatar,contact.getAvatar());

        db.insert(sTableName,null,values);

        db.close();

    }
    public void updatecontact(Usuario contact){
        SQLiteDatabase db=getWritableDatabase();

        //metodo para guardar informacion
        ContentValues values= new ContentValues();
        values.put(sColumnName,contact.getNombre());
        values.put(sColumnID,contact.getIdUsuario());
        values.put(sColumnNickName,contact.getNickName());
        values.put(sColumnCorreo,contact.getCorreo());
        values.put(sColumnContraseña,contact.getContraseña());
        values.put(sColumnGenero,contact.getGenero());
        values.put(sColumnAvatar,contact.getAvatar());
        String where= sColumnID + "=" + contact.getIdUsuario();

        db.update(sTableName, values, where, null);


        //String [] params={"" + contact.getId(),"","suus"};
        //b.update(sTableName,values,"id = ? and name = ?",params);
        db.close();
    }
    public void deleteContact(int idContact){
        SQLiteDatabase db=getWritableDatabase();

        db.delete(sTableName,sColumnID + "=" + idContact,null);

        db.close();
    }
    public Usuario getContact(int idContact){
        Usuario contact=null;
        SQLiteDatabase db=getWritableDatabase();

        String where=sColumnID + "=" + idContact;
        Cursor c = db.query(sTableName,null,null,null,null,null,null);
        if (c.moveToFirst()){
            int idUsuario=c.getInt((c.getColumnIndex("idUsuario")));
            String nickname=c.getString((c.getColumnIndex("nickName")));
            String name= c.getString(c.getColumnIndex("nombre"));
            String correo=c.getString((c.getColumnIndex("correo")));
            String contraseña=c.getString((c.getColumnIndex("contraseña")));
            String genero=c.getString((c.getColumnIndex("genero")));
            String avatar=c.getString((c.getColumnIndex("avatar")));

            contact= new Usuario(idUsuario,nickname,name,correo,contraseña,genero,avatar,null);
            c.close();
        }

        db.close();
        return contact;
    }

    public Usuario getContactCorreo(String idContact){
        Usuario contact=null;
        SQLiteDatabase db=getWritableDatabase();

        String where=sColumnID + "=" + idContact;
        Cursor c = db.query(sTableName,null,null,null,null,null,null);
        if (c.moveToFirst()){
            int idUsuario=c.getInt((c.getColumnIndex("idUsuario")));
            String nickname=c.getString((c.getColumnIndex("nickName")));
            String name= c.getString(c.getColumnIndex("nombre"));
            String correo=c.getString((c.getColumnIndex("correo")));
            String contraseña=c.getString((c.getColumnIndex("contraseña")));
            String genero=c.getString((c.getColumnIndex("genero")));
            String avatar=c.getString((c.getColumnIndex("avatar")));

            contact= new Usuario(idUsuario,nickname,name,correo,contraseña,genero,avatar,null);
            c.close();
        }

        db.close();
        return contact;
    }

}


