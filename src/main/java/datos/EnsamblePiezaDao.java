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
    private static final String SQL_LISTAR_SEGUN_TIPO_MUEBLE = "SELECT tp.nombre, r.cantidad_necesaria FROM requerimiento r JOIN tipo_pieza tp ON(r.id_tipo_pieza=tp.id_tipo_pieza) WHERE r.tipo_mueble=?";                                                                              

    @Override
    public EnsamblePieza encontrar(EnsamblePieza t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EnsamblePieza> listar(EnsamblePieza modelo) throws MisExcepciones {
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
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return requerimientos;
    }
    
    public List<EnsamblePieza> listarSegunTipoMueble(String tipoMueble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<EnsamblePieza> requerimientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR_SEGUN_TIPO_MUEBLE);
            stmt.setString(1, tipoMueble.toUpperCase());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombrePieza = rs.getString("tp.nombre");
                int cantidadNecesaria = rs.getInt("r.cantidad_necesaria");
                
                EnsamblePieza ensamblePieza = new EnsamblePieza(nombrePieza, cantidadNecesaria);
                requerimientos.add(ensamblePieza);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return requerimientos;
    }

    @Override
    public int insertar(EnsamblePieza modelo) throws MisExcepciones {
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
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
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
    public List<EnsamblePieza> listar() throws MisExcepciones{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
