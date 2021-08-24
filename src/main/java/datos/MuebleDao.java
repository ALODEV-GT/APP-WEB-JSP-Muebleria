package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Mueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MuebleDao implements Sentencias<Mueble> {

    private static final String SQL_INSERT = "INSERT INTO mueble(tipo_mueble, precio) VALUES(?,?)";
    private static final String SQL_SELECT_BY_NOMBRE = "SELECT * FROM mueble WHERE tipo_mueble=?";

    @Override
    public Mueble encontrar(Mueble modelo) throws MisExcepciones, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            rs = stmt.executeQuery();

            while (rs.next()) {
                double precio = rs.getDouble("precio");
                modelo.setPrecio(precio);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return modelo;
    }

    public boolean existe(String nombre) throws MisExcepciones, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, nombre.toUpperCase());
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return existe;
    }

    @Override
    public List<Mueble> listar(Mueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(Mueble modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            stmt.setDouble(2, modelo.getPrecio());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;

    }

    @Override
    public int eliminar(Mueble modelo) {
        return 0;
    }

    @Override
    public int actualizar(Mueble modelo) {
        return 0;
    }

    @Override
    public List<Mueble> listar() throws MisExcepciones, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}