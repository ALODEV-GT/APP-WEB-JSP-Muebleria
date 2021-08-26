package web.fabrica;

import datos.PiezaDao;
import datos.TipoPiezaDao;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Pieza;
import dominio.clases.TipoPieza;
import java.io.IOException;
import java.sql.SQLException;
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
            }
        } catch (ServletException | IOException ex) {
            ex.printStackTrace(System.out);
            throw new MisExcepciones("Ocurrio un error en FABRICA ACCIONES");
        }
    }

    public void fabricaAccionesPost(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String accionesFabrica = request.getParameter("accionFabrica");

        switch (accionesFabrica) {
            case "actualizarTipoPieza":
                this.modificarTipoPieza(request, response);
                break;
            case "agregarTipoPieza":
                this.agregarTipoPieza(request, response);
                break;
        }

    }

    private void agregarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombrenuevoTipoPieza = request.getParameter("nombreNuevoTipoPieza");
        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        tipoPiezaDao.insertar(new TipoPieza(nombrenuevoTipoPieza));
        this.materiaPrima(request, response);
    }

    private void modificarTipoPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idTipoPieza = Integer.valueOf(request.getParameter("idTipoPieza"));
        String nombreTipoPieza = request.getParameter("nombreTipoPieza");

        TipoPieza modeloTipoPieza = new TipoPieza(idTipoPieza, nombreTipoPieza);

        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        tipoPiezaDao.actualizarNombre(modeloTipoPieza);
        this.materiaPrima(request, response);
    }

    public void fabricaPaginas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String pagina = request.getParameter("pagina");

        if (pagina == null) {
            pagina = "inicio";
        }

        try {
            switch (pagina) {
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
        } catch (IOException | SQLException | ServletException ex) {
            throw new MisExcepciones("HA OCURRIDO UN ERROR EN CONTROLADOR FABRICA");
        }
    }

    private void mostrarTodasLasPiezas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        try {

            List<Pieza> piezas = new PiezaDao().listar();

            HttpSession sesion = request.getSession();
            sesion.setAttribute("todasLasPiezas", piezas);
            request.getRequestDispatcher("/WEB-INF/paginas/fabrica/infoPiezas.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new MisExcepciones("OCURRIO UN ERROR EN FABRICA MATERIA PRIMA");
        }
    }

    private void eliminarPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int idPieza = Integer.valueOf(request.getParameter("idPieza"));
        Pieza modeloPieza = new Pieza(idPieza);
        PiezaDao piezaDao = new PiezaDao();
        modeloPieza = piezaDao.encontrar(modeloPieza);
        piezaDao.eliminar(modeloPieza);
        TipoPiezaDao tipoPiezaDao = new TipoPiezaDao();
        TipoPieza modeloTipoPieza = new TipoPieza(modeloPieza.getIdTipoPieza());
        tipoPiezaDao.quitarPieza(modeloTipoPieza);
        mostrarTodasLasPiezas(request, response);
    }

    private void editarPieza(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {

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
    
    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException, SQLException {
        List<TipoPieza> piezasPorAgotar = new TipoPiezaDao().listarPiezasPorAgotar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("piezasPorAgotar", piezasPorAgotar);
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/fabrica.jsp").forward(request, response);
    }

    private void materiaPrima(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException {
        List<TipoPieza> tipoPiezas;
        try {
            tipoPiezas = new TipoPiezaDao().listar();
            HttpSession sesion = request.getSession();
            sesion.setAttribute("tipoPiezasDisponibles", tipoPiezas);
            request.getRequestDispatcher("/WEB-INF/paginas/fabrica/materiaPrima.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new MisExcepciones("OCURRIO UN ERROR EN FABRICA MATERIA PRIMA");
        }
    }

    private void produccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException {
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/produccion.jsp").forward(request, response);
    }

    private void ensamble(HttpServletRequest request, HttpServletResponse response) throws ServletException, MisExcepciones, IOException {
        request.getRequestDispatcher("/WEB-INF/paginas/fabrica/ensamble.jsp").forward(request, response);
    }

}
