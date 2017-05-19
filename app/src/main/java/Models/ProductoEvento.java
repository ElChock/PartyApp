package Models;

/**
 * Created by Ayrton on 03/05/2017.
 */

public class ProductoEvento {
    int idPEE;
    int cantidad;
    int idEvento;
    int idProducto;

    public int getIdPEE() {
        return idPEE;
    }

    public void setIdPEE(int idPEE) {
        this.idPEE = idPEE;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
