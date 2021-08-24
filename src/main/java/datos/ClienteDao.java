package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao implements Sentencias<Cliente> {

    private static final String SQL_INSERT = ("INSERT INTO cliente(nit,nombre,direccion,municipio,departamento) VALUES(?,?,?,?,?)");
    private static final String SQL_EXISTE = ("SELECT * FROM cliente WHERE nit=?");

    public boolean existe(String nit) {
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
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existe;
    }

    @Override
    public Cliente encontrar(Cliente t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listar(Cliente modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(Cliente modelo) throws SQLException {
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
            ex.printStackTrace(System.out);
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
    public List<Cliente> listar() throws MisExcepciones, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
