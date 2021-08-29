package dominio.clases;
public class Devolucion {
    private int idDevolucion;
    private int numFactura;
    private String fecha;
    private double perdida;

    public Devolucion(int numFactura, String fecha, double perdida) {
        this.numFactura = numFactura;
        this.fecha = fecha;
        this.perdida = perdida;
    }
    
    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }
    
    
}
