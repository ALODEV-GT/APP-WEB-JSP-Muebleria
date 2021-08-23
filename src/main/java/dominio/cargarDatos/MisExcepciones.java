package dominio.cargarDatos;

public class MisExcepciones extends Exception {

    public static final long SERIAL_VERSION = 500L;

    public MisExcepciones(String mensaje) {
        super(mensaje);
    }
}
