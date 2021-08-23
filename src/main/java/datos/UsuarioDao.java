package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDao implements Sentencias<Usuario> {

    private static final String SQL_SELECT_BY_NU = "SELECT u.passw, a.nombre FROM usuario u JOIN area a ON(u.id_area = a.id_area) WHERE u.nombre_usuario=?";
    private static final String SQL_INSERT = "INSERT INTO usuario VALUES(?,?,?)";
    private static final String SQL_SELECT_EXISTE_USUARIO = "SELECT * FROM usuario WHERE nombre_usuario=?";
    private static final String SQL_SELECT_EXISTE_AREA = "SELECT * FROM area WHERE id_area=?";

    public boolean existe(int idArea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_EXISTE_AREA);
            stmt.setInt(1, idArea);
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

    public boolean existe(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_EXISTE_USUARIO);
            stmt.setString(1, nombreUsuario);
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
    public Usuario encontrar(Usuario usuario) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NU);
            stmt.setString(1, usuario.getNombre());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String passw = rs.getString("u.passw");
                String area = rs.getString("a.nombre");
                usuario.setPassword(passw);
                usuario.setArea(area);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuario;
    }

    @Override
    public int insertar(Usuario usuario) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdArea());
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
    public List<Usuario> listar(Usuario modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(Usuario modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Usuario modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
