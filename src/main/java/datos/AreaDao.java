package datos;

import dominio.cargarDatos.MisExcepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDao {

    private static final String SQL_OBTENER_ID = "SELECT * FROM area WHERE nombre=? LIMIT 1";
    private static final String SQL_SELECT = "SELECT nombre FROM area";
    private static final String SQL_OBTENER_NOMBRE = "SELECT nombre FROM area WHERE id_area=?";

    
    
    public List<String> listarAreas() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> areas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String area = rs.getString("nombre");
                areas.add(area);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return areas;
    }

    public int obtenerId(String nombreArea) throws MisExcepciones {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int idArea = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID);
            stmt.setString(1, nombreArea);

            rs = stmt.executeQuery();

            while (rs.next()) {
                idArea = rs.getInt("id_area");
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return idArea;
    }
    
    public String obtenerNombreArea(int idArea) throws MisExcepciones {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String nombreArea ="";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_NOMBRE);
            stmt.setInt(1, idArea);

            rs = stmt.executeQuery();

            while (rs.next()) {
                nombreArea = rs.getString("nombre");
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return nombreArea;
    }

}
