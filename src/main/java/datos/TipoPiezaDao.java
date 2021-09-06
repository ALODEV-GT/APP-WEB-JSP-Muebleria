package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.TipoPieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPiezaDao implements Sentencias<TipoPieza> {

    //Consultas
    private static final String SQL_SELECT_BY_NOMBRE = "SELECT * FROM tipo_pieza WHERE nombre=?";
    private static final String SQL_INSERT = "INSERT INTO tipo_pieza(nombre, cantidad, eliminado) VALUES(?, 0,0)";
    private static final String SQL_UPDATE = "UPDATE tipo_pieza SET nombre = ?, cantidad = ? WHERE id_tipo_pieza = ?";
    private static final String SQL_AGREGAR_PIEZA = "UPDATE tipo_pieza SET cantidad=cantidad+1 WHERE id_tipo_pieza=?";
    private static final String SQL_QUITAR_PIEZA = "UPDATE tipo_pieza SET cantidad=cantidad-1 WHERE id_tipo_pieza=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM tipo_pieza WHERE id_tipo_pieza = ?";
    private static final String SQL_USAR_PIEZA = "UPDATE tipo_pieza SET cantidad = cantidad - 1 WHERE id_tipo_pieza = ?";
    private static final String SQL_PIEZAS_POR_AGOTAR = "SELECT * FROM tipo_pieza WHERE cantidad<20 AND eliminado = 0";
    private static final String SQL_SELECT = "SELECT * FROM tipo_pieza WHERE eliminado=0";
    private static final String SQL_UPDATE_NOMBRE = "UPDATE tipo_pieza SET nombre = ? WHERE id_tipo_pieza = ?";
    private static final String SQL_DESHABILITAR = "UPDATE tipo_pieza SET eliminado=1, cantidad=0 WHERE id_tipo_pieza=?";
    private static final String SQL_HABILITAR = "UPDATE tipo_pieza SET eliminado=0 WHERE nombre=?";
    private static final String SQL_ESTA_HABILITADO = "SELECT eliminado FROM tipo_pieza WHERE nombre=?";

    /**
     * Verifica si un tipo de pieza esta habilitado.
     * @param tipoPieza
     * @return
     * @throws MisExcepciones 
     */
    public boolean estaHabilitado(String tipoPieza) throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean habilitado = true;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ESTA_HABILITADO);
            stmt.setString(1, tipoPieza.toUpperCase());

            rs = stmt.executeQuery();

            while (rs.next()) {
                int eliminado = rs.getInt("eliminado");
                if (eliminado == 1) {
                    habilitado = false;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return habilitado;
    }
    
    /**
     * Obtiene los registros donde la cantidad existente de las piezas de ese tipo es menor a 20.
     * @return
     * @throws MisExcepciones 
     */
    public List<TipoPieza> listarPiezasPorAgotar() throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoPieza> piezas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_PIEZAS_POR_AGOTAR);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                TipoPieza tipoPieza = new TipoPieza(idTipoPieza, nombre, cantidad);
                piezas.add(tipoPieza);
            }

        } catch (SQLException ex) {
             throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return piezas;
    }

    /**
     * Resta una unidad al contador de piezas de un tipo de pieza.
     * @param idPieza
     * @throws MisExcepciones 
     */
    public void usarPieza(int idPieza) throws MisExcepciones{
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
            Conexion.close(conn);
        }
    }

    /**
     * Obtiene un registro un lo almacena en un modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    public TipoPieza encontrarById(TipoPieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, modelo.getIdTipoPieza());

            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                modelo.setNombre(nombre);
                modelo.setCantidad(cantidad);
                modelo.setIdTipoPieza(idTipoPieza);
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

    /**
     * Verifica la existencia de un tipo de pieza.
     * @param nombrePieza
     * @return
     * @throws MisExcepciones 
     */
    public boolean existe(String nombrePieza) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, nombrePieza.toUpperCase());

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
     * Obtiene un registro y lo almacena en un modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public TipoPieza encontrar(TipoPieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
            stmt.setString(1, modelo.getNombre().toUpperCase());

            rs = stmt.executeQuery();

            while (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                modelo.setCantidad(cantidad);
                modelo.setIdTipoPieza(idTipoPieza);
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
    public List<TipoPieza> listar(TipoPieza modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Agrega un nuevo registro con los valores especificados en el modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int insertar(TipoPieza modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.getNombre().toUpperCase());

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
    public int eliminar(TipoPieza modelo) throws MisExcepciones{
        return 0;
    }

    /**
     * Actualiza el atributo habilitado, especificando que el tipo de pieza sera deshabilitado.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    public int deshabilitar(TipoPieza modelo) throws MisExcepciones{
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DESHABILITAR);
            stmt.setInt(1, modelo.getIdTipoPieza());
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
     * Actualiza el atributo habilitado, especificando que el tipo de pieza sera habilitado.
     * @param nombre
     * @return
     * @throws MisExcepciones 
     */
    public int habilitar(String nombre) throws MisExcepciones {
        int numModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_HABILITAR);
            stmt.setString(1, nombre);
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
     * Actualiza el registro.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int actualizar(TipoPieza modelo) throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, modelo.getNombre());
            stmt.setDouble(2, modelo.getCantidad());
            stmt.setInt(3, modelo.getIdTipoPieza());

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
     * Actualiza el atributo nombre de un registro.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    public int actualizarNombre(TipoPieza modelo) throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_NOMBRE);
            stmt.setString(1, modelo.getNombre());
            stmt.setInt(2, modelo.getIdTipoPieza());
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
     * Aumenta una unidad el contador de piezas de un tipo determinado.
     * @param modelo
     * @throws MisExcepciones 
     */
    public void agregarPieza(TipoPieza modelo) throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_AGREGAR_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());

            stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    /**
     * Resta una unidad al contador de piezas de un tipo de pieza.
     * @param modelo
     * @throws MisExcepciones 
     */
    public void quitarPieza(TipoPieza modelo) throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUITAR_PIEZA);
            stmt.setInt(1, modelo.getIdTipoPieza());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    
    /**
     * Obtiene todos los registros que esten habilitados con su informacion(id tipo de pieza, nombre, cantidad existente).
     * @param descendente orden
     * @return
     * @throws MisExcepciones 
     */
    public List<TipoPieza> listar(boolean descendente) throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoPieza> piezas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();

            String orden = descendente ? "ORDER BY cantidad DESC" : "ORDER BY cantidad ASC";
            stmt = conn.prepareStatement(SQL_SELECT +" "+ orden);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                TipoPieza tipoPieza = new TipoPieza(idTipoPieza, nombre, cantidad);
                piezas.add(tipoPieza);
            }

        } catch (SQLException ex) {
             throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return piezas;
    }

    /**
     * Obtiene el registro de todos los muebles que esten habilitados,
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public List<TipoPieza> listar() throws MisExcepciones{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoPieza> piezas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPieza = rs.getInt("id_tipo_pieza");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                TipoPieza tipoPieza = new TipoPieza(idTipoPieza, nombre, cantidad);
                piezas.add(tipoPieza);
            }

        } catch (SQLException ex) {
             throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return piezas;
    }

}
