package Models;

import android.graphics.Bitmap;

import com.google.gson.Gson;

/**
 * Created by Ayrton on 03/05/2017.
 */

public class Usuario {
    public Usuario() {

    }
    public Usuario(String contraseña, String correo) {
        this.contraseña = contraseña;
        this.correo = correo;
    }

    public Usuario(String nombre, String correo, String contraseña, String genero, String avatar) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.genero = genero;
        this.avatar = avatar;
    }

    public Usuario(int idUsuario, String nickName, String nombre, String correo, String contraseña, String genero, Bitmap image) {
        this.idUsuario = idUsuario;
        this.nickName = nickName;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.genero = genero;
        this.image = image;
    }

    public Usuario(int idUsuario, String nickName, String nombre, String correo, String contraseña, String genero, String avatar, Bitmap image) {
        this.idUsuario = idUsuario;
        this.nickName = nickName;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.genero = genero;
        this.avatar = avatar;
        this.image = image;
    }

    int idUsuario;
    String nickName;
    String nombre;
    String correo;
    String contraseña;
    String genero;
    String avatar;
    Bitmap image;

    public Usuario(String nickName, String correo, String contraseña) {
        this.nickName = nickName;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String toJSON(){
        return new Gson().toJson(this);
    }
}
