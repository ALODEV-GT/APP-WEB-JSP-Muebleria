package datos;

import dominio.cargarDatos.MisExcepciones;
import java.sql.SQLException;
import java.util.List;

public interface Sentencias<T> {

    public T encontrar(T modelo) throws MisExcepciones, SQLException;

    public List<T> listar(T modelo) throws MisExcepciones, SQLException;

    public List<T> listar() throws MisExcepciones, SQLException;

    public int insertar(T modelo) throws MisExcepciones, SQLException;

    public int eliminar(T modelo) throws MisExcepciones, SQLException;

    public int actualizar(T modelo) throws MisExcepciones, SQLException;

}
