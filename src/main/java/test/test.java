package test;

import datos.Conexion;
import datos.UsuarioDao;
import dominio.clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

//    public static void main(String[] args) {
//
//        //        try {
////            Connection conn = Conexion.getConexion();
////            String sql = "SELECT u.passw, a.nombre FROM usuario u JOIN area a ON(u.id_area = a.id_area) WHERE u.nombre_usuario=?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setString(1, "BQUIALO");
////            
////            ResultSet rs = stmt.executeQuery();
////            while (rs.next()) {                
////                String passw = rs.getString("u.passw");
////                String area = rs.getString("a.nombre");
////                System.out.println("Password: " + passw);
////                System.out.println("Area: " + area);
////            }
////            
////        } catch (SQLException ex) {
////            ex.printStackTrace(System.out);
////        }
//
//
//
//    Usuario usuario = new Usuario("BQUIALO");
//    usuario = new UsuarioDao().encontrar(usuario);
//        System.out.println(usuario.getNombre());
//        System.out.println(usuario.getPassword());
//        System.out.println(usuario.getArea());
//    
//
//    }
}
