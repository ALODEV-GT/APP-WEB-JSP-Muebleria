package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao implements Sentencias<Cliente> {

    //Consultas
    private static final String SQL_INSERT = "INSERT INTO cliente(nit,nombre,direccion,municipio,departamento) VALUES(?,?,?,?,?)";
    private static final String SQL_EXISTE = "SELECT * FROM cliente WHERE nit=?";

    /**
     * Verifica la existencia de un cliente segun el nit.
     * @param nit
     * @return
     * @throws MisExcepciones 
     */
    public boolean existe(String nit) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, nit);
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
     * Recupera los valores de un registro almacenandolo en un modelo.
     * @param modelo
     * @return El modelo con los valores del registro.
     * @throws MisExcepciones 
     */
    @Override
    public Cliente encontrar(Cliente modelo) throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, modelo.getNit());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String municipio = rs.getString("municipio");
                String departamento = rs.getString("departamento");
                modelo.setNombre(nombre);
                modelo.setDireccion(direccion);
                modelo.setMunicipo(municipio);
                modelo.setDepartamento(departamento);
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return modelo;
    }

    @Override
    public List<Cliente> listar(Cliente modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Inserta un nuevo registro con los valores especificados en el modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int insertar(Cliente modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNit());
            stmt.setString(2, modelo.getNombre());
            stmt.setString(3, modelo.getDireccion());
            stmt.setString(4, modelo.getMunicipo());
            stmt.setString(5, modelo.getDepartamento());

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
    public int eliminar(Cliente modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Cliente modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listar() throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
