package datos;

import dominio.clases.Usuario;
import java.sql.*;

public class UsuarioDao {
    private static final String SQL_SELECT_BY_NU= "SELECT u.passw, a.nombre FROM usuario u JOIN area a ON(u.id_area = a.id_area) WHERE u.nombre_usuario=?";
    
    public Usuario encontrar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NU);
            stmt.setString(1, usuario.getNombre());
             rs = stmt.executeQuery();
             
             while(rs.next()){
                 String passw = rs.getString("u.passw");
                 String area = rs.getString("a.nombre");
                 usuario.setPassword(passw);
                 usuario.setArea(area);
             }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return usuario;
    }
}
