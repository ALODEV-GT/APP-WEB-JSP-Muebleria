package datos;

import dominio.cargarDatos.Funciones;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.EnsamblarMueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnsambleMuebleDao implements Sentencias<EnsamblarMueble> {

    private static final String SQL_INSERT = "INSERT INTO ensamble(fecha_ensamble, usuario_ensamblador,tipo_mueble,costo,vendido,devuelto) VALUES(?,?,?,?,0,0)";
    private static final String SQL_SELECCIONAR_ULTIMO = "SELECT id_ensamble FROM ensamble ORDER BY id_ensamble DESC LIMIT 1";
    private static final String SQL_SELECT = "SELECT * FROM ensamble";
    private static final String SQL_SELECT_DISPONIBLES = "SELECT e.id_ensamble, e.tipo_mueble, m.precio FROM ensamble e JOIN mueble m ON(e.tipo_mueble=m.tipo_mueble) WHERE vendido=0";
    private static final String SQL_EXISTE = "SELECT * FROM ensamble WHERE id_ensamble =? AND vendido=0";
    private static final String SQL_VENDER = "UPDATE ensamble SET vendido=1 WHERE id_ensamble=?";
    private static final String SQL_DEVOLVER = "UPDATE ensamble SET devuelto=1 WHERE id_ensamble=?";
    private static final String SQL_OBTENER_PRECIO = "SELECT m.precio FROM ensamble e JOIN mueble m ON(e.tipo_mueble=m.tipo_mueble)  WHERE id_ensamble=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM ensamble WHERE id_ensamble=?";

    public void devolver(int idEnsamble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DEVOLVER);
            stmt.setInt(1, idEnsamble);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    @Override
    public EnsamblarMueble encontrar(EnsamblarMueble modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EnsamblarMueble ensamblarMueble = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, modelo.getIdEnsamble());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaEnsamble = rs.getString("fecha_ensamble");
                String usuarioEnsamblador = rs.getString("usuario_ensamblador");
                String tipoMueble = rs.getString("tipo_mueble");
                double costo = rs.getDouble("costo");
                int vendido = rs.getInt("vendido");
                int devuelto = rs.getInt("devuelto");
                ensamblarMueble = new EnsamblarMueble(modelo.getIdEnsamble(), fechaEnsamble, usuarioEnsamblador,
                        tipoMueble, costo, vendido, devuelto);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return ensamblarMueble;
    }

    public double obtenerPrecio(int idEnsamble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double precio = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_PRECIO);
            stmt.setInt(1, idEnsamble);
            rs = stmt.executeQuery();

            while (rs.next()) {
                precio = rs.getDouble("m.precio");
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return precio;
    }

    public void venderMueble(int idEnsamble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_VENDER);
            stmt.setInt(1, idEnsamble);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public boolean estaEnVenta(int idEnsamble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setInt(1, idEnsamble);
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

    public List<EnsamblarMueble> listarDisponibles() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EnsamblarMueble> ensamblesMueble = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DISPONIBLES);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idEnsamble = rs.getInt("e.id_ensamble");
                String tipoMueble = rs.getString("e.tipo_mueble");
                double precioVenta = rs.getDouble("m.precio");

                EnsamblarMueble ensambleMueble = new EnsamblarMueble(idEnsamble, tipoMueble, precioVenta);
                ensamblesMueble.add(ensambleMueble);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return ensamblesMueble;
    }

    @Override
    public List<EnsamblarMueble> listar(EnsamblarMueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int obtenerIdUltimoEnviado() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idEnsamble = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECCIONAR_ULTIMO);

            rs = stmt.executeQuery();

            while (rs.next()) {
                idEnsamble = rs.getInt("id_ensamble");
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return idEnsamble;
    }

    @Override
    public int insertar(EnsamblarMueble modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getFechaEnsambre());
            stmt.setString(2, modelo.getEnsamblador());
            stmt.setString(3, modelo.getTipoMueble());
            stmt.setDouble(4, modelo.getCosto());

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
    public int eliminar(EnsamblarMueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(EnsamblarMueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<EnsamblarMueble> listar(boolean descendente) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EnsamblarMueble> ensamblesMueble = new ArrayList<>();

        try {
            String orden = descendente ? "ORDER BY fecha_ensamble DESC" : "ORDER BY fecha_ensamble ASC";
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT + " " + orden);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idEnsamble = rs.getInt("id_ensamble");
                String fechaEnsamble = Funciones.formatearFechaEnAEs(rs.getString("fecha_ensamble"));
                String usuarioEnsamblador = rs.getString("usuario_ensamblador");
                String tipoMueble = rs.getString("tipo_mueble");
                double costo = rs.getDouble("costo");
                int vendido = rs.getInt("vendido");

                EnsamblarMueble ensambleMueble = new EnsamblarMueble(idEnsamble, fechaEnsamble, usuarioEnsamblador, tipoMueble, costo, vendido);
                ensamblesMueble.add(ensambleMueble);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return ensamblesMueble;
    }

    @Override
    public List<EnsamblarMueble> listar() throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
