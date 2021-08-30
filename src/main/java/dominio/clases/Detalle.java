package dominio.clases;

public class Detalle {

    private int numDetalle;
    private int numFactura;
    private int idEnsamble;
    private double precio;
    private String nombreProducto;
    private String fechaCompra;

    public Detalle(int numFactura, int idEnsamble, double precio, String nombreProducto, String fechaCompra) {
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
        this.fechaCompra = fechaCompra;
    }
    
    

    public Detalle(int numDetalle, int numFactura, int idEnsamble, double precio) {
        this.numDetalle = numDetalle;
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
    }

    public Detalle(int numDetalle, int numFactura, int idEnsamble, double precio, String nombrePieza) {
        this.numDetalle = numDetalle;
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
        this.nombreProducto = nombrePieza;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombrePieza) {
        this.nombreProducto = nombrePieza;
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
