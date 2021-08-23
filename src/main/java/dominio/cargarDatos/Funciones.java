package dominio.cargarDatos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funciones {

    public static String formatearFecha(String fechaString) throws DateTimeException {
        DateTimeFormatter formatoARecibir = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoADevolver = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, formatoARecibir);
        String fechaFormateada = fecha.format(formatoADevolver);
        return fechaFormateada;
    }

    public static String formaterFechaRecibida(LocalDate fecha)  throws DateTimeException{
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fecha.format(formato);
        return fechaFormateada;
    }
}
