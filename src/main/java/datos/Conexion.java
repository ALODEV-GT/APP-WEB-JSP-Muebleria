package datos;

import dominio.cargarDatos.MisExcepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/MI_MUEBLERIA?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "brayan";
    private static final String JDBC_PASSWORD = "contra123";

    private static BasicDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {

            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);
        }

        return dataSource;
    }

    public static Connection getConnection() throws MisExcepciones {
        try {
            return getDataSource().getConnection();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal con la base de datos, vuelve a intentarlo");
        }
    }

    public static void close(ResultSet rs) throws MisExcepciones {
        try {
            rs.close();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al cerrar un recurso");
        }
    }

    public static void close(PreparedStatement stmt) throws MisExcepciones {
        try {
            stmt.close();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al cerrar un recurso");
        }
    }

    public static void close(Connection conn) throws MisExcepciones {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al cerrar la conexion");
        }
    }

}
