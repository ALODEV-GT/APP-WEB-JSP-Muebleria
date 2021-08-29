package dominio.cargarDatos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funciones {

    public static String formatearFechaEsAEn(String fechaString) throws DateTimeException {
        DateTimeFormatter formatoARecibir = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoADevolver = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, formatoARecibir);
        String fechaFormateada = fecha.format(formatoADevolver);
        return fechaFormateada;
    }

    public static String formatearFechaEnAEs(String fechaString)  throws DateTimeException{
        DateTimeFormatter formatoARecibir = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoADevolver = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatoARecibir);
        String fechaFormateada = fecha.format(formatoADevolver);
        return fechaFormateada;
    }
}
