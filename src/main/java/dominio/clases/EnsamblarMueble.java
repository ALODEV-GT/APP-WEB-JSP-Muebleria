package dominio.clases;

import java.sql.Date;

public class EnsamblarMueble {

    private int idEnsamble;
    private String tipoMueble;
    private String ensamblador;
    private Date fechaEnsambre;
    private double costo;

    public EnsamblarMueble() {
    }

    public EnsamblarMueble(String tipoMueble, String ensamblador, Date fechaEnsambre, double costo) {
        this.tipoMueble = tipoMueble;
        this.ensamblador = ensamblador;
        this.fechaEnsambre = fechaEnsambre;
        this.costo = costo;
    }

    public EnsamblarMueble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public EnsamblarMueble(Date fechaEnsambre) {
        this.fechaEnsambre = fechaEnsambre;
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public String getEnsamblador() {
        return ensamblador;
    }

    public void setEnsamblador(String ensamblador) {
        this.ensamblador = ensamblador;
    }

    public Date getFechaEnsambre() {
        return fechaEnsambre;
    }

    public void setFechaEnsambre(Date fechaEnsambre) {
        this.fechaEnsambre = fechaEnsambre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

}
