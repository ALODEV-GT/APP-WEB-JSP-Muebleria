package dominio.cargarDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CargarDatos {

    private final File archivoCarga;

    public CargarDatos(File archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public void cargarDatos() {
        String[] lineasArchivo;
        String encabezado;
        ArrayList<String> campos;
        lineasArchivo = listarLineasArchivo(this.archivoCarga);
        for (int i = 0; i < lineasArchivo.length; i++) {
            encabezado = extraerEncabezado(lineasArchivo[i]);
            campos = extraerCampos(lineasArchivo[i]);
            procesarLinea(encabezado, campos, i);
        }
    }

    private void procesarLinea(String encabezado, ArrayList<String> campos, int indice) {

        switch (encabezado) {
            case "USUARIO":
                cargarUsuario(encabezado, campos, indice);
                break;
            case "PIEZA":
                cargarPieza(encabezado, campos, indice);
                break;
            case "MUEBLE":
                cargarMueble(encabezado, campos, indice);
                break;
            case "ENSAMBLE_PIEZAS":
                cargarEnsamblePiezas(encabezado, campos, indice);
                break;
            case "ENSAMBLAR_MUEBLE":
                cargarEnsamblarMueble(encabezado, campos, indice);
                break;
            case "CLIENTE":
                cargarCliente(encabezado, campos, indice);
                break;
        }
    }

    public String[] listarLineasArchivo(File archivoCarga) {
        ArrayList<String> listaLineasArchivo = new ArrayList();

        try {
            FileReader archivo = new FileReader(archivoCarga);
            BufferedReader lector = new BufferedReader(archivo);

            String linea = lector.readLine();
            while (linea != null) {
                listaLineasArchivo.add(linea);
                linea = lector.readLine();
            }
            lector.close();
        } catch (FileNotFoundException ex) {
            //
        } catch (IOException ex) {
            //
        }

        String[] lista = new String[listaLineasArchivo.size()];
        listaLineasArchivo.toArray(lista);

        return lista;
    }

    private String extraerEncabezado(String lineaArchivo) {
        String[] cadena;
        String encabezado;
        cadena = lineaArchivo.split("\\(");
        encabezado = cadena[0];
        return encabezado;
    }

    public ArrayList<String> extraerCampos(String lineaArchivo) {
        ArrayList<String> camposIndividualesAL = null;
        try {
            String[] cadena;
            cadena = lineaArchivo.split("\\(");
            String campos = lineaArchivo.substring(cadena[0].length() + 1, lineaArchivo.length() - 1);
            String[] camposIndividuales = campos.split(",");
            camposIndividualesAL = new ArrayList<>(Arrays.asList(camposIndividuales));

        } catch (StringIndexOutOfBoundsException e) {
            //
        }
        return camposIndividualesAL;
    }

    private void cargarUsuario(String encabezado, ArrayList<String> campos, int indice) {
        String usuario = campos.get(0);
        String password = campos.get(1);
        int id_area = Integer.valueOf(campos.get(2));
    }

    private void cargarPieza(String encabezado, ArrayList<String> campos, int indice) {
        String tipo = campos.get(0);
        double precio = Double.valueOf(campos.get(1));
    }

    private void cargarMueble(String encabezado, ArrayList<String> campos, int indice) {
        String nombre = campos.get(0);
        double precio = Double.valueOf(campos.get(1));
    }

    private void cargarEnsamblePiezas(String encabezado, ArrayList<String> campos, int indice) {
        String tipoMueble = campos.get(0);
        String pieza = campos.get(1);
        int cantidadPieza = Integer.valueOf(campos.get(2));
    }

    private void cargarEnsamblarMueble(String encabezado, ArrayList<String> campos, int indice) {
        String tipoMueble = campos.get(0);
        String ensamblador = campos.get(1);
        String fechaEnsamble = campos.get(2);
    }

    private void cargarCliente(String encabezado, ArrayList<String> campos, int indice) {
        String nombre = campos.get(0);
        String nit = campos.get(1);
        String direccion = campos.get(2);
        String municipio = campos.get(3);
        String departamento = campos.get(4);
    }

}
