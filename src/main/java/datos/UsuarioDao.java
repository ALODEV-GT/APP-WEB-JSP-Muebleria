package datos;

import dominio.Usuario;
import java.sql.*;

public class UsuarioDao {
    private static final String SQL_SELECT_BY_NU= "SELECT nombre_usuario, passw FROM usuario WHERE nombre_usuario= ?";
    
    public Usuario encontrar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        return null;
    }
}
