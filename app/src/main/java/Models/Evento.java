package Models;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Ayrton on 03/05/2017.
 */

public class Evento {

    int idEvento;
    String nombre;
    String lugarLa;
    String lugarLo;
    String descripcion;
    Date fecha;
    Date fechaCreacion;
    int privado;
    String imagenPath;
    int idCreador;

    public Evento(int idEvento, String nombre, String lugarLa, String lugarLo, String descripcion, Date fecha, Date fechaCreacion, int privado, String imagenPath, int idCreador) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.lugarLa = lugarLa;
        this.lugarLo = lugarLo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.fechaCreacion = fechaCreacion;
        this.privado = privado;
        this.imagenPath = imagenPath;
        this.idCreador = idCreador;
    }

    public Evento() {
    }


    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugarLa() {
        return lugarLa;
    }

    public void setLugarLa(String lugarLa) {
        this.lugarLa = lugarLa;
    }

    public String getLugarLo() {
        return lugarLo;
    }

    public void setLugarLo(String lugarLo) {
        this.lugarLo = lugarLo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getPrivado() {
        return privado;
    }

    public void setPrivado(int privado) {
        this.privado = privado;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }

    public String toJSON(){
        return new Gson().toJson(this);
    }
}
