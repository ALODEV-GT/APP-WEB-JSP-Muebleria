package test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {

    public static void main(String[] args) {
        
            String fechaFactura = "2021-08-22";
            compararFechas(fechaFactura);
            
        
        
        
        
//        String cadena="1 ,  5 , 6,  7 ,   8    , 9,   ,     , ,    13   ,5  1 , ,   8, ,   9  4  ,  8 ,       9 ,  4,  ";
//        String limpia = cadena.replace(" ", "");
//        String[] idMueblesS = limpia.split(",");
//
//        Set<Integer> ids= new HashSet<>();
//        for (int i = 0; i < idMueblesS.length; i++) {
//            
//            if (!idMueblesS[i].isEmpty()) {
//               ids.add(Integer.valueOf(idMueblesS[i]));
//            }
//        }
//        
//        for(Integer as: ids){
//            System.out.println(as + " otra vez " +
//             as + " otra vez " + as);
//        }
        

//        LocalDate fechald = LocalDate.now();
//        String fecha = fechald.toString();
//        System.out.println(fecha);
    }
    
    private static boolean compararFechas(String fechaFactura){
        boolean aceptable = false;
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaFac = LocalDate.parse(fechaFactura);
        long semanas = fechaFac.until(fechaActual, ChronoUnit.WEEKS);
        if (semanas == 0) {
            aceptable = true;
        }
        return aceptable;
    }
    
//        System.out.println(formatearFecha("12/09/2021"));
//        
//        LocalDate fecha = LocalDate.of(2021, 9, 27);
//        System.out.println(formaterFechaRecibida(fecha));
//
//    }
//
//    public static String formatearFecha(String fechaString) throws DateTimeException {
//        DateTimeFormatter formatoARecibir = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        DateTimeFormatter formatoADevolver = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate fecha = LocalDate.parse(fechaString, formatoARecibir);
//        String fechaFormateada = fecha.format(formatoADevolver);
//        return fechaFormateada;
//    }
//    
//    public static String formaterFechaRecibida(LocalDate fecha){
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String fechaFormateada = fecha.format(formato);
//        return fechaFormateada;
//    }
}
