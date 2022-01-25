package datos;

import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Armado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArmadoDao implements Sentencias<Armado> {
    
    //Consultas
    private static final String SQL_INSERT = "INSERT INTO armado(id_pieza_usada,id_ensamble) VALUES(?,?)";
    private static final String SQL_SELECT = "SELECT tp.nombre, p.precio, a.id_pieza_usada FROM armado a JOIN pieza p ON(a.id_pieza_usada = p.id_pieza) JOIN tipo_pieza tp ON(p.id_tipo_pieza = tp.id_tipo_pieza) WHERE a.id_ensamble=?";
    
    @Override
    public Armado encontrar(Armado modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Armado> listar(Armado t) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Armado> listar() throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Lista el id de las piezas usadas en el ensamble con el id que se recibe como parametro..
     * @param idEnsamble 
     * @return Una lista con el id de las piezas usadas en el ensamble.
     * @throws MisExcepciones 
     */
    public List<Armado> listarSegunIdEnsamble(int idEnsamble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Armado> armados = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idEnsamble);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombrePieza = rs.getString("tp.nombre");
                double precio = rs.getDouble("p.precio");
                int idPiezaUsada = rs.getInt("a.id_pieza_usada");
                Armado armado = new Armado(nombrePieza, precio);
                armado.setIdPiezaUsada(idPiezaUsada);
                armados.add(armado);
            }
            
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return armados;
    }
    
    /**
     * Inserta un registos utilizando los valores especificados en el modelo.
     * @param modelo
     * @return
     * @throws MisExcepciones 
     */
    @Override
    public int insertar(Armado modelo) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        int numModificados = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, modelo.getIdPiezaUsada());
            stmt.setInt(2, modelo.getIdEnsamble());
            
            numModificados = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(stmt);
        }
        return numModificados;
    }
    
    @Override
    public int eliminar(Armado modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int actualizar(Armado modelo) throws MisExcepciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
