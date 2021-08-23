package dominio.clases;

public class EnsamblarMueble {

    private int idEnsamble;
    private String fechaEnsambre;
    private String ensamblador;
    private String tipoMueble;
    private double costo;
    private int vendido;

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }

    public EnsamblarMueble() {
    }

    public EnsamblarMueble(String tipoMueble, String ensamblador, String fechaEnsambre, double costo) {
        this.tipoMueble = tipoMueble;
        this.ensamblador = ensamblador;
        this.fechaEnsambre = fechaEnsambre;
        this.costo = costo;
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
