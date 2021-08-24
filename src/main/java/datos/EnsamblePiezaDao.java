package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.EnsamblePieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnsamblePiezaDao implements Sentencias<EnsamblePieza> {

    private static final String SQL_INSERT = "INSERT INTO requerimiento(tipo_mueble,id_tipo_pieza,cantidad_necesaria) VALUES(?,?,?)";
    private static final String SQL_SELEC_BY_TIPO_MUEBLE = "SELECT * FROM requerimiento WHERE tipo_mueble = ?";

    @Override
    public EnsamblePieza encontrar(EnsamblePieza t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EnsamblePieza> listar(EnsamblePieza modelo) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EnsamblePieza ensamblePieza = null;
        List<EnsamblePieza> requerimientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELEC_BY_TIPO_MUEBLE);
            stmt.setString(1, modelo.getTipoMueble().toUpperCase());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numRequesito = rs.getInt("num_requisito");
                String tipoMueble = rs.getString("tipo_mueble");
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                int cantidadNecesaria = rs.getInt("cantidad_necesaria");
                ensamblePieza = new EnsamblePieza(numRequesito, tipoMueble, idTipoPieza, cantidadNecesaria);
                requerimientos.add(ensamblePieza);
            }

        } catch (SQLException ex) {

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return requerimientos;
    }

    @Override
    public int insertar(EnsamblePieza modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getTipoMueble().toUpperCase());
            stmt.setDouble(2, modelo.getIdTipoPieza());
            stmt.setInt(3, modelo.getCantidadPieza());

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
    public int eliminar(EnsamblePieza modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(EnsamblePieza modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EnsamblePieza> listar() throws MisExcepciones, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
