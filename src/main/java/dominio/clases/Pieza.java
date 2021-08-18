package dominio.clases;

public class Pieza {

    private int idPieza;
    private String tipoPieza;
    private double precio;

    public Pieza(String tipoPieza, double precio) {
        this.tipoPieza = tipoPieza;
        this.precio = precio;
    }

    public Pieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public String getTipoPieza() {
        return tipoPieza;
    }

    public double getPrecio() {
        return precio;
    }

    public void setTipoPieza(String tipoPieza) {
        this.tipoPieza = tipoPieza;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
