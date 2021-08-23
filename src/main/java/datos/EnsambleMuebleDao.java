package datos;

import dominio.clases.EnsamblarMueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EnsambleMuebleDao implements Sentencias<EnsamblarMueble> {

    private static final String SQL_INSERT = "INSERT INTO ensamble(fecha_ensamble, usuario_ensamblador,tipo_mueble,costo,vendido) VALUES(?,?,?,?,0)";

    @Override
    public EnsamblarMueble encontrar(EnsamblarMueble t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EnsamblarMueble> listar(EnsamblarMueble modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(EnsamblarMueble modelo) {
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
            ex.printStackTrace(System.out);
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

}
