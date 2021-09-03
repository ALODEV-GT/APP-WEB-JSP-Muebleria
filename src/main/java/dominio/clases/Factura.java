package dominio.clases;

import java.text.DecimalFormat;
import java.util.List;

public class Factura {

    private int numFactura;
    private String nitCliente;
    private String nombreCliente;
    private String fecha;
    private String vendedor;
    private List<Detalle> detalles;
    private double total;

    public Factura(int numFactura, String nitCliente, String nombreCliente, String fecha, String vendedor, double total) {
        this.numFactura = numFactura;
        this.nitCliente = nitCliente;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.total = total;
    }

    public Factura(String nitCliente, String fecha, String vendedor) {
        this.nitCliente = nitCliente;
        this.fecha = fecha;
        this.vendedor = vendedor;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public double getTotal() {
        return Double.valueOf(new DecimalFormat("#.00").format(total));
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

}
