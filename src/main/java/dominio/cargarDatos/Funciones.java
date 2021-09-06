package dominio.cargarDatos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funciones {

    /**
     * Recibe una fecha en formato dd/mm/yyyy y lo convierte a un formato yyyy-mm-dd
     * @param fechaString
     * @return
     * @throws DateTimeException 
     */
    public static String formatearFechaEsAEn(String fechaString) throws DateTimeException {
        DateTimeFormatter formatoARecibir = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoADevolver = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, formatoARecibir);
        String fechaFormateada = fecha.format(formatoADevolver);
        return fechaFormateada;
    }

    /**
     * Recibe una fecha en formato yyyy-mm-dd y lo convierte a un formato dd/mm/yyyy
     * @param fechaString
     * @return
     * @throws DateTimeException 
     */
    public static String formatearFechaEnAEs(String fechaString)  throws DateTimeException{
        DateTimeFormatter formatoARecibir = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoADevolver = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatoARecibir);
        String fechaFormateada = fecha.format(formatoADevolver);
        return fechaFormateada;
    }
}
