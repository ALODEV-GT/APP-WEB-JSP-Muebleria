package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    
    
    private static Connection conn = null;

    private Conexion() {
        String url = "jdbc:mysql://localhost:3306/MI_MUEBLERIA?useSSL=false&useTimezone=true&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
        String usuario = "brayan";
        String password = "contra123";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            new Conexion();
        }

        return conn;
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar el Result set");
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar el PreparedStatement");
            ex.printStackTrace(System.out);
        }
    }

}
