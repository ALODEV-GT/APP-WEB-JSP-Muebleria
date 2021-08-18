package dominio.clases;

public class EnsamblePieza {

    private int numRequisito; //Llave primaria
    private String tipoMueble; //Llave primaria
    private String pieza;
    private int cantidadPieza;

    public EnsamblePieza() {
    }

    public EnsamblePieza(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public EnsamblePieza(int numRequisito, String tipoMueble, String pieza, int cantidadPieza) {
        this.numRequisito = numRequisito;
        this.tipoMueble = tipoMueble;
        this.pieza = pieza;
        this.cantidadPieza = cantidadPieza;
    }

    public EnsamblePieza(String tipoMueble, String pieza, int cantidadPieza) {
        this.tipoMueble = tipoMueble;
        this.pieza = pieza;
        this.cantidadPieza = cantidadPieza;
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public String getPieza() {
        return pieza;
    }

    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    public int getCantidadPieza() {
        return cantidadPieza;
    }

    public void setCantidadPieza(int cantidadPieza) {
        this.cantidadPieza = cantidadPieza;
    }

    public int getNumRequisito() {
        return numRequisito;
    }

}
