package dominio.clases;
public class ConsultasAdministracion {
    private String fechaVenta;
    private String nombreProducto;
    private double precioVenta;
    private int numFactura;
    private String fechaDevolucion;
    private String nombreCliente;
    private String usuarioVendedor;
    private double perdida;
    private double costo;
    private double ganancia;
    private int idEnsamble;

    public ConsultasAdministracion(String fechaVenta, String nombreProducto, double precioVenta) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
    }
    
    public ConsultasAdministracion(String fechaVenta, double precioVenta, int numFactura, int idEnsamble) {
        this.fechaVenta = fechaVenta;
        this.precioVenta = precioVenta;
        this.numFactura = numFactura;
        this.idEnsamble = idEnsamble;
    }
    
    public ConsultasAdministracion(String fechaVenta, String nombreProducto, double precioVenta, String usuarioVendedor, int numFactura) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.usuarioVendedor = usuarioVendedor;
        this.numFactura = numFactura;
    }

    public ConsultasAdministracion(String fechaVenta, String nombreProducto, int numFactura, String fechaDevolucion, String nombreCliente, String usuarioVendedor, double perdida) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.numFactura = numFactura;
        this.fechaDevolucion = fechaDevolucion;
        this.nombreCliente = nombreCliente;
        this.usuarioVendedor = usuarioVendedor;
        this.perdida = perdida;
    }

    public ConsultasAdministracion(String fechaVenta, String nombreProducto, double precioVenta, double costo, double ganancia) {
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.costo = costo;
        this.ganancia = ganancia;
    }

    public ConsultasAdministracion() {
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }
    
    
    
    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getUsuarioVendedor() {
        return usuarioVendedor;
    }

    public void setUsuarioVendedor(String usuarioVendedor) {
        this.usuarioVendedor = usuarioVendedor;
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }
}