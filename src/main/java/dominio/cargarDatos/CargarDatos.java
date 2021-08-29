package dominio.cargarDatos;

import datos.ArmadoDao;
import datos.ClienteDao;
import datos.EnsambleMuebleDao;
import datos.EnsamblePiezaDao;
import datos.MuebleDao;
import datos.PiezaDao;
import datos.TipoPiezaDao;
import datos.UsuarioDao;
import dominio.clases.Armado;
import dominio.clases.Cliente;
import dominio.clases.EnsamblarMueble;
import dominio.clases.EnsamblePieza;
import dominio.clases.Mueble;
import dominio.clases.Pieza;
import dominio.clases.TipoPieza;
import dominio.clases.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;

public class CargarDatos {

    private final File archivoCarga;
    private String errores = "";

    public CargarDatos(File archivoCarga) {
        this.archivoCarga = archivoCarga;
        cargarDatos();
    }

    private void cargarDatos() {
        String[] lineasArchivo;
        String encabezado;
        ArrayList<String> campos;
        lineasArchivo = listarLineasArchivo(this.archivoCarga);
        for (int i = 0; i < lineasArchivo.length; i++) {
            encabezado = extraerEncabezado(lineasArchivo[i]);
            campos = extraerCampos(quitarComillasYEspacios(lineasArchivo[i]));
            try {
                procesarLinea(encabezado, campos);
            } catch (MisExcepciones ex) {
                agregarError("Linea " + (i + 1) + ": " + ex.getMessage());
            }
        }
        System.out.println(errores);
    }

    private String quitarComillasYEspacios(String linea) {
        String nuevaLinea = "";
        String[] comillas = linea.split("\"");
        for (String p : comillas) {
            nuevaLinea += p;
        }
        return nuevaLinea;
    }

    private void procesarLinea(String encabezado, ArrayList<String> campos) throws MisExcepciones {

        switch (encabezado) {
            case "USUARIO":
                cargarUsuario(campos);
                break;
            case "PIEZA":
                cargarPieza(campos);
                break;
            case "MUEBLE":
                cargarMueble(campos);
                break;
            case "ENSAMBLE_PIEZAS":
                cargarEnsamblePiezas(campos);
                break;
            case "ENSAMBLAR_MUEBLE":
                cargarEnsamblarMueble(campos);
                break;
            case "CLIENTE":
                cargarCliente(campos);
                break;
        }
    }

    private String[] listarLineasArchivo(File archivoCarga) {
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

    private ArrayList<String> extraerCampos(String lineaArchivo) {
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

    private void agregarError(String error) {
        this.errores += error + "%";
    }

    private void cargarUsuario(ArrayList<String> campos) throws MisExcepciones {
        try {
            if (campos.size() != 3) {
                throw new MisExcepciones("Es necesario ingresar (\"nombre\",\"contrasena\",id_area) ");
            }

            String nombreUsuario = campos.get(0);
            String password = campos.get(1);
            int idArea = Integer.valueOf(campos.get(2));

            if (new UsuarioDao().existe(nombreUsuario)) {
                throw new MisExcepciones("Este nombre de usuario ya esta en uso");
            }

            if (!(new UsuarioDao().existe(idArea))) {
                throw new MisExcepciones("El area no existe");
            }

            if (nombreUsuario.length() > 30) {
                throw new MisExcepciones("El nombre de usuario es muy largo. Max 30 caracteres");
            }
            if (password.length() > 25) {
                throw new MisExcepciones("La contrasena es muy larga. Max 25 caracteres");
            }

            Usuario usuario = new Usuario(nombreUsuario, password, idArea);
            UsuarioDao insertarUsuario = new UsuarioDao();
            insertarUsuario.insertar(usuario);
        } catch (NumberFormatException ex) {
            throw new MisExcepciones("Debes igresar un numero para id area: 1. Fabrica, 2. Punto de venta, 3. Financiero");
        }
    }

    private void cargarPieza(ArrayList<String> campos) throws MisExcepciones {
        try {
            if (campos.size() != 2) {
                throw new MisExcepciones("Es necesario ingresar (\"Tipo pieza\",\"precio\") ");
            }

            String tipoPieza = campos.get(0);
            double precio = Double.valueOf(campos.get(1));

            if (String.valueOf(precio).length() > 8) {
                throw new MisExcepciones("El precio es muy elevado. Solo se admiten hasta 99999.99. Con un maximo de 2 decimales");
            }

            if (tipoPieza.length() > 45) {
                throw new MisExcepciones("El nombre del tipo de pieza es muy largo. Maximo: 45 caracteres");
            }

            TipoPiezaDao tipoPiezaDAO = new TipoPiezaDao();
            TipoPieza modelo = new TipoPieza(tipoPieza);

            if (!tipoPiezaDAO.existe(tipoPieza)) {
                tipoPiezaDAO.insertar(modelo);
            }

            tipoPiezaDAO.habilitar(tipoPieza);

            modelo = tipoPiezaDAO.encontrar(modelo);

            int idTipoPieza = modelo.getIdTipoPieza();
            Pieza nuevaPieza = new Pieza(idTipoPieza, precio);
            PiezaDao piezaDao = new PiezaDao();
            piezaDao.insertar(nuevaPieza);
            tipoPiezaDAO.agregarPieza(modelo);

        } catch (NumberFormatException ex) {
            throw new MisExcepciones("En el precio ingresa un numero decimal o un entero");
        }
    }

    private void cargarMueble(ArrayList<String> campos) throws MisExcepciones {

        try {

            if (campos.size() != 2) {
                throw new MisExcepciones("Es necesario ingresar (\"Nombre del mueble\",\"Precio\") ");
            }

            String nombre = campos.get(0);
            double precio = Double.valueOf(campos.get(1));

            MuebleDao muebleDao = new MuebleDao();

            if (String.valueOf(precio).length() > 8) {
                throw new MisExcepciones("El precio es muy elevado. Solo se admiten hasta 99999.99. Con un maximo de 2 decimales");
            }

            if (nombre.length() > 45) {
                throw new MisExcepciones("El nombre del mueble es muy largo. Maximo: 45 caracteres");
            }

            if (muebleDao.existe(nombre)) {
                throw new MisExcepciones("Este mueble ya existe");
            }

            Mueble modelo = new Mueble(nombre, precio);
            muebleDao.insertar(modelo);

        } catch (NumberFormatException ex) {
            throw new MisExcepciones("En el precio ingresa un numero decimal o un entero");
        }
    }

    private void cargarEnsamblePiezas(ArrayList<String> campos) throws MisExcepciones {
        try {
            if (campos.size() != 3) {
                throw new MisExcepciones("Es necesario ingresar (\"Mueble\",\"Pieza\",\"cantidad\")");
            }

            String tipoMueble = campos.get(0);
            String pieza = campos.get(1);
            int cantidadPieza = Integer.valueOf(campos.get(2));

            if (tipoMueble.length() > 45) {
                throw new MisExcepciones("El nombre del mueble es muy largo. Max: 45 caracteres");
            }

            if (pieza.length() > 45) {
                throw new MisExcepciones("El nombre de la pieza que ingresaste es muy largo. Max 45 caracteres");
            }

            MuebleDao muebleDao = new MuebleDao();
            if (!muebleDao.existe(tipoMueble)) {
                throw new MisExcepciones("Primero debes crear el mueble para asignarle piezas");
            }

            TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
            if (!tipoPiezaDao.existe(pieza)) {
                throw new MisExcepciones("Primero debes crear la pieza para asignarlo a un mueble");
            }

            TipoPieza modeloTipoPieza = new TipoPieza(pieza);
            modeloTipoPieza = tipoPiezaDao.encontrar(modeloTipoPieza);

            EnsamblePiezaDao ensamblePiezaDao = new EnsamblePiezaDao();
            EnsamblePieza modeloEnsamblePieza = new EnsamblePieza(tipoMueble, modeloTipoPieza.getIdTipoPieza(), cantidadPieza);
            ensamblePiezaDao.insertar(modeloEnsamblePieza);

        } catch (NumberFormatException ex) {
            throw new MisExcepciones("En cantidad ingresa un numero entero");
        }
    }

    private void cargarEnsamblarMueble(ArrayList<String> campos) throws MisExcepciones {
        try {

            if (campos.size() != 3) {
                throw new MisExcepciones("Es necesario ingresar (\"Mueble\",\"Usuario\",\"Fecha\")");
            }

            String tipoMueble = campos.get(0);
            String ensamblador = campos.get(1);
            String fechaEnsamble = campos.get(2);

            Funciones.formatearFechaEsAEn(fechaEnsamble);

            if (tipoMueble.length() > 45) {
                throw new MisExcepciones("El nombre del mueble es muy largo. Max: 45 caracteres");
            }

            if (ensamblador.length() > 30) {
                throw new MisExcepciones("El nombre de usuario que ingresaste es muy largo. Max 30 caracteres");
            }

            MuebleDao muebleDao = new MuebleDao();
            UsuarioDao usuarioDao = new UsuarioDao();

            if (!muebleDao.existe(tipoMueble)) {
                throw new MisExcepciones("El mueble que tratas de ensamblar no existe");
            }

            if (!usuarioDao.existe(ensamblador)) {
                throw new MisExcepciones("El nombre de usuario que ingresaste no le pertenece a nadie");
            }

            Usuario usuario = new Usuario(ensamblador);
            usuario = usuarioDao.encontrar(usuario);

            if (!usuario.getArea().equalsIgnoreCase("Fabrica")) {
                throw new MisExcepciones("Este usuario no pertenece al area de fabricacion");
            }

            EnsamblePiezaDao ensamblePiezaDao = new EnsamblePiezaDao();
            EnsamblePieza modeloEnsamblePieza = new EnsamblePieza(tipoMueble);
            ArrayList<EnsamblePieza> requerimientos = new ArrayList<>(ensamblePiezaDao.listar(modeloEnsamblePieza));

            TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
            
            if (requerimientos.isEmpty()) {
                throw new MisExcepciones("No es posible ensamblar el mueble, ya que se desconoce como se debe construir");
            }

            for (EnsamblePieza ep : requerimientos) {
                TipoPieza modeloTipoPieza = new TipoPieza(ep.getIdTipoPieza());
                modeloTipoPieza = tipoPiezaDao.encontrarById(modeloTipoPieza);
                if (ep.getCantidadPieza() > modeloTipoPieza.getCantidad()) {
                    throw new MisExcepciones("No tienes suficientes piezas de " + modeloTipoPieza.getNombre() + " para ensamblar este mueble");
                }
            }

            PiezaDao piezaDao = new PiezaDao();
            ArrayList<Integer> idPiezasUsadas = new ArrayList<>();
            double costo = 0;
            for (int i = 0; i < requerimientos.size(); i++) {
                for (int j = 0; j < requerimientos.get(i).getCantidadPieza(); j++) {
                    Pieza modelo = new Pieza();
                    modelo.setIdTipoPieza(requerimientos.get(i).getIdTipoPieza());
                    modelo = piezaDao.encotrarNoUsadosByIdTipoPieza(modelo);
                    int idPieza = modelo.getIdPieza();
                    idPiezasUsadas.add(idPieza);
                    piezaDao.usarPieza(idPieza);
                    tipoPiezaDao.usarPieza(modelo.getIdTipoPieza());
                    costo += modelo.getPrecio();
                }
            }

            EnsambleMuebleDao ensambleMuebleDao = new EnsambleMuebleDao();
            EnsamblarMueble ensambleMueble = new EnsamblarMueble(tipoMueble, ensamblador, Funciones.formatearFechaEsAEn(fechaEnsamble), costo);
            ensambleMuebleDao.insertar(ensambleMueble);
            
            int idEnsamble = ensambleMuebleDao.obtenerIdUltimoEnviado();
            
            ArmadoDao armadoDao = new ArmadoDao();
            for (int i = 0; i < idPiezasUsadas.size(); i++) {
                armadoDao.insertar(new Armado(idPiezasUsadas.get(i), idEnsamble));
            }

        } catch (DateTimeException ex) {
            throw new MisExcepciones("El formato de fecha es incorrecto. Debes ingresarlo en este formato dd/MM/yyyy");
        }
    }

    private void cargarCliente(ArrayList<String> campos) throws MisExcepciones {
        if (campos.size() != 3 && campos.size() != 5) {
            throw new MisExcepciones("Es necesario ingresar (\"Nombre\",\"Nit\",\"direccion\")  o  (\"Nombre\",\"Nit\",\"direccion\",\"Municipio\",\"Departamento\")");
        }

        String nit = campos.get(1);
        String nombre = campos.get(0);
        String direccion = campos.get(2);
        String municipio = "";
        String departamento = "";

        if (nombre.length() > 50) {
            throw new MisExcepciones("El nombre es muy largo. Max: 45 caracteres");
        }

        if (nit.length() > 15) {
            throw new MisExcepciones("El numero de nit, es muy largo");
        }

        ClienteDao clienteDao = new ClienteDao();
        if (clienteDao.existe(nit)) {
            throw new MisExcepciones("Este cliente ya existe");
        }

        if (direccion.length() > 50) {
            throw new MisExcepciones("La direccion es muy larga. Max: 45 caracteres");
        }

        if (campos.size() == 5) {
            municipio = campos.get(3);
            departamento = campos.get(4);

            if (municipio.length() > 50) {
                throw new MisExcepciones("El nombre del municipio es muy largo. Max: 45 caracteres");
            }

            if (departamento.length() > 50) {
                throw new MisExcepciones("El nombre del departamento es muy largo. Max: 45 caracteres");
            }
        }

        Cliente modelo = null;

        if (campos.size() == 5) {
            modelo = new Cliente(nombre, nit, direccion, municipio, departamento);
        } else if (campos.size() == 3) {
            modelo = new Cliente(nombre, nit, direccion);
        }

        clienteDao.insertar(modelo);
    }

    public String getErrores() {
        return this.errores;
    }
}
