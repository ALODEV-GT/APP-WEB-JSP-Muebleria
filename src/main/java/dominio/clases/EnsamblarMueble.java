package dominio.clases;

import java.util.List;

public class EnsamblarMueble {

    private int idEnsamble;
    private String fechaEnsambre;
    private String ensamblador;
    private String tipoMueble;
    private double costo;
    private double precioVenta;
    private int vendido;
    private List<Armado> piezasUsadas;
    private int devuelto;

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }

    public EnsamblarMueble() {
    }

    public List<Armado> getPiezasUsadas() {
        return piezasUsadas;
    }

    public void setPiezasUsadas(List<Armado> piezasUsadas) {
        this.piezasUsadas = piezasUsadas;
    }

    public EnsamblarMueble(int idEnsamble, String tipoMueble, double precioVenta) {
        this.idEnsamble = idEnsamble;
        this.tipoMueble = tipoMueble;
        this.precioVenta = precioVenta;
    }

    public EnsamblarMueble(int idEnsamble, String fechaEnsambre, String ensamblador, String tipoMueble, double costo, int vendido, int devuelto) {
        this.idEnsamble = idEnsamble;
        this.fechaEnsambre = fechaEnsambre;
        this.ensamblador = ensamblador;
        this.tipoMueble = tipoMueble;
        this.costo = costo;
        this.vendido = vendido;
        this.devuelto = devuelto;
    }
    
    
    
    public EnsamblarMueble(int idEnsamble, String fechaEnsambre, String ensamblador, String tipoMueble, double costo, int vendido) {
        this.idEnsamble = idEnsamble;
        this.fechaEnsambre = fechaEnsambre;
        this.ensamblador = ensamblador;
        this.tipoMueble = tipoMueble;
        this.costo = costo;
        this.vendido = vendido;
    }
    
    public EnsamblarMueble(String tipoMueble, String ensamblador, String fechaEnsambre, double costo) {
        this.tipoMueble = tipoMueble;
        this.ensamblador = ensamblador;
        this.fechaEnsambre = fechaEnsambre;
        this.costo = costo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(int devuelto) {
        this.devuelto = devuelto;
    }
    
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public EnsamblarMueble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public EnsamblarMueble(String fechaEnsambre) {
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

    public String getFechaEnsambre() {
        return fechaEnsambre;
    }

    public void setFechaEnsambre(String fechaEnsambre) {
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
