package datos;

import dominio.cargarDatos.Funciones;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Devolucion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolucionDao implements Sentencias<Devolucion> {

    //Consultas
    private static final String SQL_INSERT = "INSERT INTO devolucion(id_mueble_devuelto,num_factura,fecha,perdida) VALUES(?,?,?,?)";
    private static final String SQL_OBTENER_ALL_DEVOLUCIONES = "SELECT dev.num_factura, c.nombre, f.nit_cliente, dev.fecha, dev.id_mueble_devuelto, e.tipo_mueble FROM devolucion dev JOIN factura f ON(dev.num_factura=f.num_factura) JOIN cliente c ON(f.nit_cliente=c.nit) JOIN ensamble e ON(dev.id_mueble_devuelto=e.id_ensamble)";
    private static final String SQL_OBTENER_DEVOLUCIONES_BY_NIT_AND_FECHA = "SELECT dev.num_factura, c.nombre, f.nit_cliente, dev.fecha, dev.id_mueble_devuelto, e.tipo_mueble FROM devolucion dev JOIN factura f ON(dev.num_factura=f.num_factura) JOIN cliente c ON(f.nit_cliente=c.nit) JOIN ensamble e ON(dev.id_mueble_devuelto=e.id_ensamble) WHERE dev.fecha BETWEEN ? AND ? AND f.nit_cliente=? ORDER BY dev.fecha DESC";

    /**
     * Obtiene los registros de las devoluciones de un cliente en un intervalo de fecha determinada.
     * @param nitC nit del cliente
     * @param fechaInicial
     * @param fechaFinal
     * @return
     * @throws MisExcepciones 
     */
    public List<Devolucion> obtenerDevolucionesByNitAndFecha(String nitC, String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Devolucion> devoluciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_DEVOLUCIONES_BY_NIT_AND_FECHA);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, nitC);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("dev.num_factura");
                String nombreCliente = rs.getString("c.nombre");
                String nit = rs.getString("f.nit_cliente");
                String fecha = rs.getString("dev.fecha");
                int idMuebleDevuelto = rs.getInt("dev.id_mueble_devuelto");
                String producto = rs.getString("e.tipo_mueble");

                Devolucion devolucion = new Devolucion(idMuebleDevuelto, numFactura, Funciones.formatearFechaEnAEs(fecha), nombreCliente, nit, producto);
                devoluciones.add(devolucion);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return devoluciones;
    }

    /**
     * Obtiene el registro de todas las devoluciones.
     * @return
     * @throws MisExcepciones 
     */
    public List<Devolucion> obtenerAllDevoluciones() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Devolucion> devoluciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ALL_DEVOLUCIONES);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("dev.num_factura");
                String nombreCliente = rs.getString("c.nombre");
                String nit = rs.getString("f.nit_cliente");
                String fecha = rs.getString("dev.fecha");
                int idMuebleDevuelto = rs.getInt("dev.id_mueble_devuelto");
                String producto = rs.getString("e.tipo_mueble");

                Devolucion devolucion = new Devolucion(idMuebleDevuelto, numFactura, Funciones.formatearFechaEnAEs(fecha), nombreCliente, nit, producto);
                devoluciones.add(devolucion);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return devoluciones;
    }

    /**
     * Inserta un nuevo registro de una devolucion con los valores especificados en el modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int insertar(Devolucion modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getIdDevolucion());
            stmt.setInt(2, modelo.getNumFactura());
            stmt.setString(3, modelo.getFecha());
            stmt.setDouble(4, modelo.getPerdida());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
        return numModificados;
    }

    @Override
    public Devolucion encontrar(Devolucion modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devolucion> listar(Devolucion t) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devolucion> listar() throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(Devolucion modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Devolucion modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
