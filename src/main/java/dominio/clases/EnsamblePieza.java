package dominio.clases;

public class EnsamblePieza {

    private int numRequisito; //Llave primaria
    private String tipoMueble;
    private int idTipoPieza;
    private String nombrePieza;
    private int cantidadPieza;

    public EnsamblePieza() {
    }

    public EnsamblePieza(String nombrePieza, int cantidadPieza) {
        this.nombrePieza = nombrePieza;
        this.cantidadPieza = cantidadPieza;
    }

    public EnsamblePieza(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public EnsamblePieza(int numRequisito, String tipoMueble, int idTipoPieza, int cantidadPieza) {
        this.numRequisito = numRequisito;
        this.tipoMueble = tipoMueble;
        this.idTipoPieza = idTipoPieza;
        this.cantidadPieza = cantidadPieza;
    }

    public EnsamblePieza(String tipoMueble, int idTipoPieza, int cantidadPieza) {
        this.tipoMueble = tipoMueble;
        this.idTipoPieza = idTipoPieza;
        this.cantidadPieza = cantidadPieza;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public void setIdTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public int getIdTipoPieza() {
        return idTipoPieza;
    }

    public void setIdTipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
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
