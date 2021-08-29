package datos;

import dominio.cargarDatos.MisExcepciones;
import java.util.List;

public interface Sentencias<T> {

    public T encontrar(T modelo) throws MisExcepciones;

    public List<T> listar(T modelo) throws MisExcepciones;

    public List<T> listar() throws MisExcepciones;

    public int insertar(T modelo) throws MisExcepciones;

    public int eliminar(T modelo) throws MisExcepciones;

    public int actualizar(T modelo) throws MisExcepciones;

}
