package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiezaDao implements Sentencias<Pieza> {

    //Consultas
    private static final String SQL_INSERT = "INSERT INTO pieza(id_tipo_pieza,precio,usado) VALUES(?,?,0)";
    private static final String SQL_SELECT_BY_TIPO_PIEZA = "SELECT * FROM pieza WHERE id_tipo_pieza=? AND usado=0";
    private static final String SQL_USAR_PIEZA = "UPDATE pieza SET usado = 1 WHERE id_pieza = ?";
    private static final String SQL_SELECT = "SELECT p.id_pieza, tp.nombre, p.precio FROM pieza p JOIN tipo_pieza tp ON (p.id_tipo_pieza = tp.id_tipo_pieza) WHERE p.usado = 0";
    private static final String SQL_DELETE = "DELETE FROM pieza WHERE id_pieza =?";
    private static final String SQL_SELECT_BY_ID_PIEZA = "SELECT p.id_tipo_pieza, p.precio, p.usado, tp.nombre FROM pieza p JOIN tipo_pieza tp ON (p.id_tipo_pieza = tp.id_tipo_pieza)  WHERE id_pieza=? ";
    private static final String SQL_DELETE_BY_ID_TIPO_PIEZA = "DELETE FROM pieza WHERE id_tipo_pieza=? && usado=0";
    private static final String SQL_UPDATE_PRECIO = "UPDATE pieza SET  precio=? WHERE id_pieza=?";
    private static final String SQL_UPDATE_REHUSAR = "UPDATE pieza SET precio=precio*0.6666 , usado=0 WHERE id_pieza=?";

    /**
     * Actualiza el precio de un registro.
     * El precio de esta pieza se devaluara 1/3 de su valor inicial.
     * @param idPieza
     * @throws MisExcepciones 
     */
    public void rehusarPieza(int idPieza) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_REHUSAR);
            stmt.setInt(1, idPieza);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
    }

    /**
     * Obtiene un registro almacenandolo en un modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public Pieza encontrar(Pieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_PIEZA);
            stmt.setInt(1, modelo.getIdPieza());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                double precio = rs.getDouble("precio");
                int usado = rs.getInt("usado");
                String nombreTipoPieza = rs.getString("tp.nombre");

                modelo.setIdTipoPieza(idTipoPieza);
                modelo.setPrecio(precio);
                modelo.setUsado(usado);
                modelo.setTipoPieza(nombreTipoPieza);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return modelo;
    }

    /**
     * Agrega un nuevo registro con los valores especificandos en el modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int insertar(Pieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getIdTipoPieza());
            stmt.setDouble(2, modelo.getPrecio());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }

        return numModificados;
    }

    /**
     * Actualiza el atributo usado, especificando que esta pieza ya fue usada.
     * @param idPieza
     * @throws MisExcepciones 
     */
    public void usarPieza(int idPieza) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_USAR_PIEZA);
            stmt.setInt(1, idPieza);

            int numMod = stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
    }

    /**
     * Obtiene el registro de una pieza que aun no ha sido usada.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    public Pieza encotrarNoUsadosByIdTipoPieza(Pieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_TIPO_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());
            rs = stmt.executeQuery();

            boolean primero = false;
            while (!primero) {
                rs.next();
                int idPieza = rs.getInt("id_pieza");
                double precio = rs.getDouble("precio");
                modelo.setIdPieza(idPieza);
                modelo.setPrecio(precio);
                primero = true;
            }
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return modelo;
    }

    /**
     * Elimina el rigistro de una pieza.
     * @param modelo pieza a eliminar.
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int eliminar(Pieza modelo) throws MisExcepciones {
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setDouble(1, modelo.getIdPieza());
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
        return numModificados;
    }

    /**
     * Elimina todas las piezas que sean de un tipo de pieza determinado.
     * @param idTipoPieza id del tipo de pieza.
     * @throws MisExcepciones 
     */
    public void eleminarSegunTipoPieza(int idTipoPieza) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_ID_TIPO_PIEZA);
            stmt.setDouble(1, idTipoPieza);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
    }

    /**
     * Actualiza el atributo precio de un registro. 
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int actualizar(Pieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, modelo.getPrecio());
            stmt.setInt(2, modelo.getIdPieza());

            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
        return numModificados;
    }

    @Override
    public List<Pieza> listar(Pieza t) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obtiene la informacion(id pieza, tipo de pieza, precio) de todos los registros que aun no han sido usados.
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public List<Pieza> listar() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPieza = rs.getInt("p.id_pieza");
                String nombreTipoPieza = rs.getString("tp.nombre");
                double precio = rs.getDouble("p.precio");

                Pieza pieza = new Pieza(idPieza, nombreTipoPieza, precio);
                piezas.add(pieza);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return piezas;
    }

}
