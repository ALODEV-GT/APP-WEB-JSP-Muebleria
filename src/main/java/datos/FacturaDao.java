package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacturaDao implements Sentencias<Factura> {

    private static final String SQL_INSERT = "INSERT INTO factura(nit_cliente,fecha,vendedor) VALUES(?,?,?)";
    private static final String SQL_SELECCIONAR_ULTIMO = "SELECT num_factura FROM factura ORDER BY num_factura DESC LIMIT 1";
    private static final String SQL_ENCONTRAR_BY_NUM_FACTURA = "SELECT * FROM factura WHERE num_factura=?";

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
            Conexion.close(conn);
        }

        return numFactura;
    }

    @Override
    public Factura encontrar(Factura modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
                String fecha= rs.getString("fecha");
                String vendedor = rs.getString("vendedor");
                
                factura = new Factura(nitCliente, fecha, vendedor);
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return factura;
    }

    @Override
    public List<Factura> listar(Factura t) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Factura> listar() throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
            Conexion.close(conn);
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
