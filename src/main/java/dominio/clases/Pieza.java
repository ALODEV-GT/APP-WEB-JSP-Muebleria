package dominio.clases;

public class Pieza {

    private int idTipoPieza;
    private int idPieza;
    private String tipoPieza;
    private double precio;
    private int usado;

    public Pieza(){
        
    }
    
    public Pieza(int idTipoPieza, double precio) {
        this.idTipoPieza = idTipoPieza;
        this.precio = precio;
    }

    public Pieza(int idPieza, String tipoPieza, double precio) {
        this.idPieza = idPieza;
        this.tipoPieza = tipoPieza;
        this.precio = precio;
    }
    
    public Pieza(int idTipoPieza, int idPieza, String tipoPieza, double precio) {
        this.idTipoPieza = idTipoPieza;
        this.idPieza = idPieza;
        this.tipoPieza = tipoPieza;
        this.precio = precio;
    }

    public Pieza(String tipoPieza, double precio) {
        this.tipoPieza = tipoPieza;
        this.precio = precio;
    }

    public Pieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public int getIdTipoPieza() {
        return idTipoPieza;
    }

    public void setIdTipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public void setUsado(int usado) {
        this.usado = usado;
    }

    public int getUsado() {
        return usado;
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
