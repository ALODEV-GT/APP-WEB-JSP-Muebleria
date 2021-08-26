package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiezaDao implements Sentencias<Pieza> {

    private static final String SQL_INSERT = "INSERT INTO pieza(id_tipo_pieza,precio,usado) VALUES(?,?,0)";
    private static final String SQL_SELECT_BY_TIPO_PIEZA = "SELECT * FROM pieza WHERE id_tipo_pieza=? AND usado=0";
    private static final String SQL_USAR_PIEZA = "UPDATE pieza SET usado = 1 WHERE id_pieza = ?";
    private static final String SQL_SELECT = "SELECT p.id_pieza, tp.nombre, p.precio FROM pieza p JOIN tipo_pieza tp ON (p.id_tipo_pieza = tp.id_tipo_pieza) WHERE p.usado = 0";
    private static final String SQL_DELETE = "DELETE FROM pieza WHERE id_pieza =?";
    private static final String SQL_SELECT_BY_ID_PIEZA = "SELECT * FROM pieza WHERE id_pieza=? ";
    private static final String SQL_DELETE_BY_ID_TIPO_PIEZA = "DELETE FROM pieza WHERE id_tipo_pieza=? && usado=0";

    @Override
    public Pieza encontrar(Pieza modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_PIEZA);
            stmt.setInt(1, modelo.getIdPieza());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                double precio = rs.getDouble("precio");
                int usado = rs.getInt("usado");
                modelo.setIdTipoPieza(idTipoPieza);
                modelo.setPrecio(precio);
                modelo.setUsado(usado);
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

    @Override
    public int insertar(Pieza modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getIdTipoPieza());
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

    public void usarPieza(int idPieza) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_USAR_PIEZA);
            stmt.setInt(1, idPieza);

            int numMod = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public Pieza encotrarNoUsadosByIdTipoPieza(Pieza modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_TIPO_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());
            rs = stmt.executeQuery();

            boolean primero = false;
            while (!primero) {
                rs.next();
                int idPieza = rs.getInt("id_pieza");
                double precio = rs.getDouble("precio");
                modelo.setIdPieza(idPieza);
                modelo.setPrecio(precio);
                primero = true;
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

    @Override
    public int eliminar(Pieza modelo) {
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setDouble(1, modelo.getIdPieza());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public void eleminarSegunTipoPieza(int idTipoPieza) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_ID_TIPO_PIEZA);
            stmt.setDouble(1, idTipoPieza);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    @Override
    public int actualizar(Pieza modelo) {
        return 0;
    }

    @Override
    public List<Pieza> listar(Pieza t) throws MisExcepciones, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pieza> listar() throws MisExcepciones, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPieza = rs.getInt("p.id_pieza");
                String nombreTipoPieza = rs.getString("tp.nombre");
                double precio = rs.getDouble("p.precio");

                Pieza pieza = new Pieza(idPieza, nombreTipoPieza, precio);
                piezas.add(pieza);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return piezas;
    }

}
