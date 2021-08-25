package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.TipoPieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPiezaDao implements Sentencias<TipoPieza> {

    private static final String SQL_SELECT_BY_NOMBRE = "SELECT * FROM tipo_pieza WHERE nombre=?";
    private static final String SQL_INSERT = "INSERT INTO tipo_pieza(nombre, cantidad) VALUES(?, 0)";
    private static final String SQL_UPDATE = "UPDATE tipo_pieza SET nombre = ?, cantidad = ? WHERE id_tipo_pieza = ?";
    private static final String SQL_AGREGAR_PIEZA = "UPDATE tipo_pieza SET cantidad=cantidad+1 WHERE id_tipo_pieza=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM tipo_pieza WHERE id_tipo_pieza = ?";
    private static final String SQL_USAR_PIEZA = "UPDATE tipo_pieza SET cantidad = cantidad - 1 WHERE id_tipo_pieza = ?";
    private static final String SQL_PIEZAS_POR_AGOTAR = "SELECT * FROM tipo_pieza WHERE cantidad<20";
    private static final String SQL_SELECT = "SELECT * FROM tipo_pieza";
    private static final String SQL_UPDATE_NOMBRE = "UPDATE tipo_pieza SET nombre = ? WHERE id_tipo_pieza = ?";

    public List<TipoPieza> listarPiezasPorAgotar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoPieza> piezas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_PIEZAS_POR_AGOTAR);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                TipoPieza tipoPieza = new TipoPieza(idTipoPieza, nombre, cantidad);
                piezas.add(tipoPieza);
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

    public TipoPieza encontrarById(TipoPieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, modelo.getIdTipoPieza());

            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                modelo.setNombre(nombre);
                modelo.setCantidad(cantidad);
                modelo.setIdTipoPieza(idTipoPieza);
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

    public boolean existe(String nombrePieza) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, nombrePieza.toUpperCase());

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
    public TipoPieza encontrar(TipoPieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, modelo.getNombre().toUpperCase());
            boolean existe = false;

            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
                int cantidad = rs.getInt("cantidad");
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                modelo.setCantidad(cantidad);
                modelo.setIdTipoPieza(idTipoPieza);
            }

            if (!existe) {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
                throw new MisExcepciones("No existe esta pieza");
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
    public List<TipoPieza> listar(TipoPieza modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(TipoPieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNombre().toUpperCase());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new MisExcepciones("Esta pieza ya existe");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    @Override
    public int eliminar(TipoPieza modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(TipoPieza modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, modelo.getNombre());
            stmt.setDouble(2, modelo.getCantidad());
            stmt.setInt(3, modelo.getIdTipoPieza());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public int actualizarNombre(TipoPieza modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_NOMBRE);
            stmt.setString(1, modelo.getNombre());
            stmt.setInt(2, modelo.getIdTipoPieza());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    public void agregarPieza(TipoPieza modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_AGREGAR_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    @Override
    public List<TipoPieza> listar() throws MisExcepciones, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoPieza> piezas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                TipoPieza tipoPieza = new TipoPieza(idTipoPieza, nombre, cantidad);
                piezas.add(tipoPieza);
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
