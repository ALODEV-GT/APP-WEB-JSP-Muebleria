package dominio.clases;

public class TipoPieza {

    private int idTipoPieza; //Llave primaria
    private String nombre;
    private int cantidad;

    public TipoPieza() {
    }

    public TipoPieza(String nombre) {
        this.nombre = nombre;
    }

    public TipoPieza(String nombre, int cantidad) {
        this.nombre = nombre;
    }

    public TipoPieza(int idTipoPieza, String nombre, int cantidad) {
        this.idTipoPieza = idTipoPieza;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public TipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public int getIdTipoPieza() {
        return idTipoPieza;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setIdTipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

}
