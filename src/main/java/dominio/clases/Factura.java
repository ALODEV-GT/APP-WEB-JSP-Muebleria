package dominio.clases;

public class Factura {

    private int numFactura;
    private String nitCliente;
    private String fecha;
    private String vendedor;

    public Factura(String nitCliente, String fecha, String vendedor) {
        this.nitCliente = nitCliente;
        this.fecha = fecha;
        this.vendedor = vendedor;
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
