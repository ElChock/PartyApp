package Models;

/**
 * Created by Ayrton on 03/05/2017.
 */

public class UsuarioLlevaProducto {
    int idUsuario;
    int idPee;
    int cantidad;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPee() {
        return idPee;
    }

    public void setIdPee(int idPee) {
        this.idPee = idPee;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
