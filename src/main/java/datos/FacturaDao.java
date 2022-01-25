package datos;

import dominio.cargarDatos.Funciones;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDao implements Sentencias<Factura> {

    //Consultas
    private static final String SQL_INSERT = "INSERT INTO factura(nit_cliente,fecha,vendedor) VALUES(?,?,?)";
    private static final String SQL_SELECCIONAR_ULTIMO = "SELECT num_factura FROM factura ORDER BY num_factura DESC LIMIT 1";
    private static final String SQL_ENCONTRAR_BY_NUM_FACTURA = "SELECT * FROM factura WHERE num_factura=?";
    private static final String SQL_INFO_ALL_FACTURA = "SELECT f.num_factura, c.nombre, f.nit_cliente, f.fecha, f.vendedor, f.total FROM factura f JOIN cliente c ON(f.nit_cliente=c.nit) ";
    private static final String SQL_SELECT_BY_NUM_FACTURA = "SELECT f.num_factura, c.nombre, f.nit_cliente, f.fecha, f.vendedor, f.total FROM factura f JOIN cliente c ON(f.nit_cliente=c.nit) WHERE f.num_factura=?";
    private static final String SQL_SELECT_BY_NIT_CLIENTE = "SELECT f.num_factura, c.nombre, f.nit_cliente, f.fecha, f.vendedor, f.total FROM factura f JOIN cliente c ON(f.nit_cliente=c.nit) WHERE f.nit_cliente=?";
    private static final String SQL_AGREGAR_TOTAL = "UPDATE factura SET total=? WHERE num_factura = ?";
    private static final String SQL_SELECT_BY_NIT_CLIENTES_AND_FECHAS = "SELECT f.num_factura, c.nombre, f.nit_cliente, f.fecha, f.vendedor, f.total FROM factura f JOIN cliente c ON(f.nit_cliente=c.nit) WHERE f.fecha BETWEEN ? AND ? AND f.nit_cliente=? ORDER BY f.fecha DESC";

    /**
     * Obtiene los registros de facturas en un intervalo de fechas.
     * @param fechaInicial
     * @param fechaFinal
     * @param nitCliente
     * @return
     * @throws MisExcepciones 
     */
    public List<Factura> listarByIntervaloFechas(String fechaInicial, String fechaFinal, String nitCliente) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NIT_CLIENTES_AND_FECHAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, nitCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("f.num_factura");
                String nombreCliente = rs.getString("c.nombre");
                String nit = rs.getString("f.nit_cliente");
                String fecha = rs.getString("f.fecha");
                String vendedor = rs.getString("f.vendedor");
                double total = rs.getDouble("f.total");

                Factura factura = new Factura(numFactura, nit, nombreCliente, Funciones.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return facturas;
    }

    /**
     * Obtiene el detalle(nombreCliente, nit, fecha, vendedor, total) de las compras registradas en una factura.
     * @param numFactura
     * @return
     * @throws MisExcepciones 
     */
    public List<Factura> listarByNumFactura(int numFactura) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NUM_FACTURA);
            stmt.setInt(1, numFactura);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreCliente = rs.getString("c.nombre");
                String nit = rs.getString("f.nit_cliente");
                String fecha = rs.getString("f.fecha");
                String vendedor = rs.getString("f.vendedor");
                double total = rs.getDouble("f.total");

                Factura factura = new Factura(numFactura, nit, nombreCliente, Funciones.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return facturas;
    }

    /**
     * Obtiene el detalle(numFactura, nombreCliente, nit, fecha, vendedor, total) de las compras de un cliente.
     * @param nitCliente nit del cliente.
     * @return
     * @throws MisExcepciones 
     */
    public List<Factura> listarByNitCliente(String nitCliente) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NIT_CLIENTE);
            stmt.setString(1, nitCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("f.num_factura");
                String nombreCliente = rs.getString("c.nombre");
                String nit = rs.getString("f.nit_cliente");
                String fecha = rs.getString("f.fecha");
                String vendedor = rs.getString("f.vendedor");
                double total = rs.getDouble("f.total");

                Factura factura = new Factura(numFactura, nit, nombreCliente, Funciones.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return facturas;
    }

    /**
     * Agrega el total de las compras.
     * @param total
     * @param numFactura
     * @throws MisExcepciones 
     */
    public void agregarTotal(double total, int numFactura) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_AGREGAR_TOTAL);
            stmt.setDouble(1, total);
            stmt.setInt(2, numFactura);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
    }

    /**
     * Obtiene el numero de factura de la ultima factura registrada.
     * @return
     * @throws MisExcepciones 
     */
    public int obtenerNumFactura() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numFactura = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECCIONAR_ULTIMO);

            rs = stmt.executeQuery();

            while (rs.next()) {
                numFactura = rs.getInt("num_factura");
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return numFactura;
    }

    @Override
    public Factura encontrar(Factura modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obtiene el registro de una factura almacenandola en un modelo.
     * @param numFactura
     * @return
     * @throws MisExcepciones 
     */
    public Factura encontrar(int numFactura) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Factura factura = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_BY_NUM_FACTURA);
            stmt.setInt(1, numFactura);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nitCliente = rs.getString("nit_cliente");
                String fecha = rs.getString("fecha");
                String vendedor = rs.getString("vendedor");

                factura = new Factura(nitCliente, fecha, vendedor);
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return factura;
    }

    @Override
    public List<Factura> listar(Factura t) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obtiene la informacion (numFacutar, nombreClient, nit, fecha, vendedor, total) de todas las facturas.
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public List<Factura> listar() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INFO_ALL_FACTURA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("f.num_factura");
                String nombreCliente = rs.getString("c.nombre");
                String nit = rs.getString("f.nit_cliente");
                String fecha = rs.getString("f.fecha");
                String vendedor = rs.getString("f.vendedor");
                double total = rs.getDouble("f.total");

                Factura factura = new Factura(numFactura, nit, nombreCliente,Funciones.formatearFechaEnAEs(fecha), vendedor, total);
                facturas.add(factura);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return facturas;
    }

    /**
     * Agrega un nuevo registro de una factura con los valores especificados en el modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int insertar(Factura modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNitCliente());
            stmt.setString(2, modelo.getFecha());
            stmt.setString(3, modelo.getVendedor());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
        return numModificados;
    }

    @Override
    public int eliminar(Factura modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Factura modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
