package dominio.clases;

public class Devolucion {

    private int idDevolucion;
    private int numFactura;
    private String fecha;
    private double perdida;
    private String nombreCliente;
    private String nitCliente;
    private String productoDevuelto;

    public Devolucion(int idDevolucion, int numFactura, String fecha, String nombreCliente, String nitCliente, String productoDevuelto) {
        this.idDevolucion = idDevolucion;
        this.numFactura = numFactura;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.nitCliente = nitCliente;
        this.productoDevuelto = productoDevuelto;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getProductoDevuelto() {
        return productoDevuelto;
    }

    public void setProductoDevuelto(String productoDevuelto) {
        this.productoDevuelto = productoDevuelto;
    }

    public Devolucion(int idDevolucion, int numFactura, String fecha, double perdida) {
        this.idDevolucion = idDevolucion;
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
