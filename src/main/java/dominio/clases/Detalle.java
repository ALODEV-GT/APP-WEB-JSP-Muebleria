package dominio.clases;
public class Detalle {
    private int numDetalle;
    private int numFactura;
    private int idEnsamble;
    private double precio;

    public Detalle(int numDetalle, int numFactura, int idEnsamble, double precio) {
        this.numDetalle = numDetalle;
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
    }

    public int getNumDetalle() {
        return numDetalle;
    }

    public void setNumDetalle(int numDetalle) {
        this.numDetalle = numDetalle;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
