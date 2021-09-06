package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements Sentencias<Usuario> {

    //Consultas
    private static final String SQL_SELECT_BY_NU = "SELECT u.passw, a.nombre FROM usuario u JOIN area a ON(u.id_area = a.id_area) WHERE u.nombre_usuario=?";
    private static final String SQL_INSERT = "INSERT INTO usuario VALUES(?,?,?,0)";
    private static final String SQL_SELECT_EXISTE_USUARIO = "SELECT * FROM usuario WHERE nombre_usuario=?";
    private static final String SQL_SELECT_EXISTE_AREA = "SELECT * FROM area WHERE id_area=?";
    private static final String SQL_UPDATE = "UPDATE usuario SET id_area=? WHERE nombre_usuario=?";
    private static final String SQL_SELECT = "SELECT nombre_usuario, id_area FROM usuario WHERE deshabilitado=0";
    private static final String SQL_DESHABILITAR = "UPDATE usuario SET deshabilitado=1 WHERE nombre_usuario=?";
    private static final String SQL_ESTA_HABILITADO = "SELECT deshabilitado FROM usuario WHERE nombre_usuario=?";

    /**
     * Verifica si un usuario esta deshabilitado.
     * @param nombreUsuario
     * @return
     * @throws MisExcepciones 
     */
    public boolean estaDeshabilitado(String nombreUsuario) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean deshabilitado = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ESTA_HABILITADO);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("deshabilitado") == 1) {
                    deshabilitado = true;
                }
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return deshabilitado;
    }

    /**
     * Actualiza el atributo habilitado, especificando que se deshabilito.
     * @param nombreUsuario
     * @return
     * @throws MisExcepciones 
     */
    public int deshabilitar(String nombreUsuario) throws MisExcepciones {
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DESHABILITAR);
            stmt.setString(1, nombreUsuario);
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }
    
    /**
     * Verifica la existencia de un area.
     * @param idArea
     * @return
     * @throws MisExcepciones 
     */
    public boolean existe(int idArea) throws MisExcepciones {
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
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existe;
    }

    /**
     * Verefica la existencia de un usuario.
     * @param nombreUsuario
     * @return
     * @throws MisExcepciones 
     */
    public boolean existe(String nombreUsuario) throws MisExcepciones {
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
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existe;
    }

    /**
     * Obtiene un registro almacenandolo en un modelo.
     * @param usuario
     * @return
     * @throws MisExcepciones 
     */
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
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuario;
    }

    /**
     * Agrega un nuevo registro con los valores especificados en el modelo.
     * @param usuario
     * @return
     * @throws MisExcepciones 
     */
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
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
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
        return 0;
    }

    @Override
    public int actualizar(Usuario modelo) throws MisExcepciones {
        return 0;
    }

    /**
     * Actualiza el area de un usuario.
     * @param idArea
     * @param usuario
     * @return
     * @throws MisExcepciones 
     */
    public int actualizar(int idArea, String usuario) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1,idArea);
            stmt.setString(2, usuario);

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return numModificados;
    }

    /**
     * Obtiene todos los registros de los usuarios que estan habilitados.
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public List<Usuario> listar() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre_usuario");
                int idArea = rs.getInt("id_area");
                Usuario usuario = new Usuario(idArea, nombre);
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }

}
