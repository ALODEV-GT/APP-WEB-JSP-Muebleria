package web.fabrica;

import datos.ArmadoDao;
import datos.EnsambleMuebleDao;
import datos.EnsamblePiezaDao;
import datos.MuebleDao;
import datos.PiezaDao;
import datos.TipoPiezaDao;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Armado;
import dominio.clases.EnsamblarMueble;
import dominio.clases.EnsamblePieza;
import dominio.clases.Mueble;
import dominio.clases.Pieza;
import dominio.clases.TipoPieza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorFabrica {
    
    public void fabricaAccionesGET(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String accionFabrica = request.getParameter("accionFabrica");
        
        try {
            switch (accionFabrica) {
                case "editarTipoPieza":
                    editarTipoPieza(request, response);
                    break;
                case "eliminarTipoPieza":
                    eliminarTipoPieza(request, response);
                    break;
                case "agregarTipoPieza":
                    request.getRequestDispatcher("/WEB-INF/paginas/fabrica/agregarTipoPieza.jsp").forward(request, response);
                    break;
                case "inventario":
                    this.mostrarTodasLasPiezas(request, response);
                    break;
                case "editarPieza":
                    editarPieza(request, response);
                    break;
                case "eliminarPieza":
                    eliminarPieza(request, response);
                    break;
                case "agregarPieza":
                    redireccionarParaAgregarPieza(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }
    
    public void ensamblarMueble(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String tipoMueble = request.getParameter("idMuebleEnsamblar");
        String usuarioEnsamblador = request.getParameter("usuario");
        String fechaEnsamble = request.getParameter("fechaEnsamble");
        
        EnsamblePiezaDao ensamblePiezaDao = new EnsamblePiezaDao();
        EnsamblePieza modeloEnsamblePieza = new EnsamblePieza(tipoMueble);
        ArrayList<EnsamblePieza> requerimientos = new ArrayList<>(ensamblePiezaDao.listar(modeloEnsamblePieza));
        
        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        
        if (requerimientos.isEmpty()) {
            throw new MisExcepciones("No es posible ensamblar el mueble, ya que no se han definido sus piezas");
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
        EnsamblarMueble ensambleMueble = new EnsamblarMueble(tipoMueble, usuarioEnsamblador, fechaEnsamble, costo);
        ensambleMuebleDao.insertar(ensambleMueble);
        
        int idEnsamble = ensambleMuebleDao.obtenerIdUltimoEnviado();
        
        ArmadoDao armadoDao = new ArmadoDao();
        for (int i = 0; i < idPiezasUsadas.size(); i++) {
            armadoDao.insertar(new Armado(idPiezasUsadas.get(i), idEnsamble));
        }
        
        this.produccion(request, response);
    }
    
    public void fabricaAccionesPost(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String accionesFabrica = request.getParameter("accionFabrica");
        
        try {
            switch (accionesFabrica) {
                case "actualizarTipoPieza":
                    this.modificarTipoPieza(request, response);
                    break;
                case "agregarTipoPieza":
                    this.agregarTipoPieza(request, response);
                    break;
                case "actualizarPieza":
                    this.modificarPieza(request, response);
                    break;
                case "agregarPieza":
                    this.agregarPieza(request, response);
                case "ensamblarMueble":
                    ensamblarMueble(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }
    
    public void fabricaPaginas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String paginaFabrica = request.getParameter("paginaFabrica");
        
        if (paginaFabrica == null) {
            paginaFabrica = "inicio";
        }
        
        try {
            switch (paginaFabrica) {
                case "inicio":
                    inicio(request, response);
                    break;
                case "materiaPrima":
                    materiaPrima(request, response);
                    break;
                case "produccion":
                    produccion(request, response);
                    break;
                case "ensamble":
                    ensamble(request, response);
                    break;
                case "AgregarTipoPieza":
                    agregarTipoPieza(request, response);
                    break;
                default:
                
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }
    
    private void redireccionarParaAgregarPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        List<TipoPieza> tipoPiezas = new TipoPiezaDao().listar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("tipoPiezas", tipoPiezas);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/agregarPieza.jsp").forward(request, response);
    }
    
    private void agregarPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String tipoPieza = request.getParameter("tipoPieza");
        double precio = Double.valueOf(request.getParameter("precioPieza"));
        
        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        TipoPieza modelotipoPieza = tipoPiezaDao.encontrar(new TipoPieza(tipoPieza));
        
        new PiezaDao().insertar(new Pieza(modelotipoPieza.getIdTipoPieza(), precio));
        
        tipoPiezaDao.agregarPieza(modelotipoPieza);
        
        this.mostrarTodasLasPiezas(request, response);
    }
    
    private void modificarPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idPieza = Integer.valueOf(request.getParameter("idPieza"));
        Double precioPieza = Double.valueOf(request.getParameter("precioPieza"));
        
        Pieza modeloPieza = new Pieza();
        modeloPieza.setIdPieza(idPieza);
        modeloPieza.setPrecio(precioPieza);
        new PiezaDao().actualizar(modeloPieza);
        this.mostrarTodasLasPiezas(request, response);
    }
    
    private void agregarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombrenuevoTipoPieza = request.getParameter("nombreNuevoTipoPieza");
        
        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        
        if (tipoPiezaDao.existe(nombrenuevoTipoPieza)) {
            throw new MisExcepciones("Esta pieza ya existe");
        }
        
        tipoPiezaDao.insertar(new TipoPieza(nombrenuevoTipoPieza));
        this.materiaPrima(request, response);
    }
    
    private void modificarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idTipoPieza = Integer.valueOf(request.getParameter("idTipoPieza"));
        String nombreTipoPieza = request.getParameter("nombreTipoPieza");
        
        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        
        if (tipoPiezaDao.existe(nombreTipoPieza)) {
            throw new MisExcepciones("Esta pieza ya existe");
        }
        
        TipoPieza modeloTipoPieza = new TipoPieza(idTipoPieza, nombreTipoPieza);
        
        tipoPiezaDao.actualizarNombre(modeloTipoPieza);
        this.materiaPrima(request, response);
    }
    
    private void mostrarTodasLasPiezas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        
        List<Pieza> piezas = new PiezaDao().listar();
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("todasLasPiezas", piezas);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/infoPiezas.jsp").forward(request, response);
    }
    
    private void eliminarPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idPieza = Integer.valueOf(request.getParameter("idPieza"));
        Pieza modeloPieza = new Pieza(idPieza);
        PiezaDao piezaDao = new PiezaDao();
        modeloPieza = piezaDao.encontrar(modeloPieza);
        piezaDao.eliminar(modeloPieza);
        new TipoPiezaDao().quitarPieza(new TipoPieza(modeloPieza.getIdTipoPieza()));
        mostrarTodasLasPiezas(request, response);
    }
    
    private void editarPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idPieza = Integer.valueOf(request.getParameter("idPieza"));
        Pieza modeloPieza = new PiezaDao().encontrar(new Pieza(idPieza));
        request.setAttribute("modeloPieza", modeloPieza);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/editarPieza.jsp").forward(request, response);
    }
    
    private void editarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idTipoPieza = Integer.valueOf(request.getParameter("idTipoPieza"));
        TipoPieza modeloTipoPieza = new TipoPiezaDao().encontrarById(new TipoPieza(idTipoPieza));
        request.setAttribute("modeloTipoPieza", modeloTipoPieza);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/editarTipoPieza.jsp").forward(request, response);
    }
    
    private void eliminarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idTipoPieza = Integer.valueOf(request.getParameter("idTipoPieza"));
        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        TipoPieza tipoPieza = new TipoPieza(idTipoPieza);
        PiezaDao piezaDao = new PiezaDao();
        piezaDao.eleminarSegunTipoPieza(idTipoPieza);
        tipoPiezaDao.deshabilitar(tipoPieza);
        this.materiaPrima(request, response);
    }
    
    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException {
        List<TipoPieza> piezasPorAgotar = new TipoPiezaDao().listarPiezasPorAgotar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("piezasPorAgotar", piezasPorAgotar);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/fabrica.jsp").forward(request, response);
    }
    
    private void materiaPrima(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException {
        String orden = request.getParameter("orden");
        
        List<TipoPieza> tipoPiezas;
        if (orden != null) {
            if (orden.equals("desc")) {
                tipoPiezas = new TipoPiezaDao().listar(true);
            } else {
                tipoPiezas = new TipoPiezaDao().listar(false);
            }
        } else {
            tipoPiezas = new TipoPiezaDao().listar(false); //Defualt: ascendente
        }
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("tipoPiezasDisponibles", tipoPiezas);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/materiaPrima.jsp").forward(request, response);
        
    }
    
    private void produccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException {
        String orden = request.getParameter("orden");
        
        List<EnsamblarMueble> ensamblesMueble;
        if (orden != null) {
            if (orden.equals("desc")) {
                ensamblesMueble = new EnsambleMuebleDao().listar(true);
            } else {
                ensamblesMueble = new EnsambleMuebleDao().listar(false);
            }
        } else {
            ensamblesMueble = new EnsambleMuebleDao().listar(true); //Defualt: descendente
        }
        
        for (int i = 0; i < ensamblesMueble.size(); i++) {
            int idEnsamble = ensamblesMueble.get(i).getIdEnsamble();
            List<Armado> armados = new ArmadoDao().listarSegunIdEnsamble(idEnsamble);
            ensamblesMueble.get(i).setPiezasUsadas(armados);
        }
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("ensamblesMueble", ensamblesMueble);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/produccion.jsp").forward(request, response);
    }
    
    private void ensamble(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException {
        List<Mueble> muebles = new MuebleDao().listar();
        
        for (int i = 0; i < muebles.size(); i++) {
            String nombreMueble = muebles.get(i).getNombre();
            List<EnsamblePieza> requerimientos = new EnsamblePiezaDao().listarSegunTipoMueble(nombreMueble);
            muebles.get(i).setRequerimientos(requerimientos);
        }
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("recetas", muebles);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/ensamble.jsp").forward(request, response);
    }
    
}
