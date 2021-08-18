package dominio.cargarDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CargarDatos {

    private File archivoCarga;

    public CargarDatos(File archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public String leerContenido() {
        String contenido = "";

        try {
            FileReader fr = new FileReader(archivoCarga);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea;
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return contenido;
    }

}
