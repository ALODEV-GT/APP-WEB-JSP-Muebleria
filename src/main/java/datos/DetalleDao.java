package datos;

import dominio.cargarDatos.Funciones;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Detalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleDao implements Sentencias<Detalle> {

    private static final String SQL_INSERT = "INSERT INTO detalle(num_detalle,num_factura,id_ensamble,precio) VALUES(?,?,?,?)";
    private static final String SQL_EXISTE_COMPRA = "SELECT * FROM detalle WHERE num_factura=? AND id_ensamble=?";
    private static final String SQL_OBTENER_DETALLES = "SELECT d.num_detalle, d.id_ensamble, d.precio, e.tipo_mueble FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) WHERE num_factura = ? ORDER BY d.num_detalle ASC";
    private static final String SQL_VENTAS_DIARIAS = "SELECT f.fecha, f.num_factura, d.id_ensamble, e.tipo_mueble, d.precio FROM detalle d JOIN factura f ON(d.num_factura=f.num_factura) JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) WHERE f.fecha=?";
    private static final String SQL_COMPRA_ALL_CLIENTES = "SELECT f.fecha, c.nombre, f.num_factura, d.id_ensamble, e.tipo_mueble, d.precio FROM detalle d JOIN factura f ON(d.num_factura=f.num_factura) JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN cliente c ON(f.nit_cliente=c.nit) ORDER BY f.fecha DESC";
    
    
    public List<Detalle> obtenerComprasAllClientes() throws MisExcepciones{
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Detalle> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_COMPRA_ALL_CLIENTES);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaF = rs.getString("f.fecha");
                int numFactura = rs.getInt("f.num_factura");
                int idEnsamble = rs.getInt("d.id_ensamble");
                String tipoMueble = rs.getString("e.tipo_mueble");
                double precio = rs.getDouble("d.precio");

                Detalle detalle = new Detalle(numFactura, idEnsamble, precio, tipoMueble, Funciones.formatearFechaEnAEs(fechaF));
                detalles.add(detalle);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }
    
    
    
    
    public List<Detalle> obtenerVentasDia(String fecha) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Detalle> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_VENTAS_DIARIAS);
            stmt.setString(1, fecha);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaF = rs.getString("f.fecha");
                int numFactura = rs.getInt("f.num_factura");
                int idEnsamble = rs.getInt("d.id_ensamble");
                String tipoMueble = rs.getString("e.tipo_mueble");
                double precio = rs.getDouble("d.precio");

                Detalle detalle = new Detalle(numFactura, idEnsamble, precio, tipoMueble, fechaF);
                detalles.add(detalle);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }

    public List<Detalle> obtenerDetalleFactura(int numFactura) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Detalle> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_DETALLES);
            stmt.setInt(1, numFactura);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numDetalle = rs.getInt("d.num_detalle");
                int idEnsamble = rs.getInt("d.id_ensamble");
                double precio = rs.getDouble("d.precio");
                String producto = rs.getString("e.tipo_mueble");

                Detalle detalle = new Detalle(numDetalle, numFactura, idEnsamble, precio, producto);
                detalles.add(detalle);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }

    public boolean existeCompra(int numFactura, int idMueble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE_COMPRA);
            stmt.setInt(1, numFactura);
            stmt.setInt(2, idMueble);
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existe;
    }

    @Override
    public Detalle encontrar(Detalle modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Detalle> listar(Detalle t) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Detalle> listar() throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(Detalle modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getNumDetalle());
            stmt.setInt(2, modelo.getNumFactura());
            stmt.setInt(3, modelo.getIdEnsamble());
            stmt.setDouble(4, modelo.getPrecio());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    @Override
    public int eliminar(Detalle modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Detalle modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
