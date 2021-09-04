package web.administracion;

import datos.AreaDao;
import datos.ConsultasAdministracionDao;
import datos.EnsamblePiezaDao;
import datos.MuebleDao;
import datos.TipoPiezaDao;
import datos.UsuarioDao;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.ConsultasAdministracion;
import dominio.clases.EnsamblePieza;
import dominio.clases.Mueble;
import dominio.clases.TipoPieza;
import dominio.clases.Usuario;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorAdministracion {

    public void administracionAccionesGet(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String accionAdministracion = request.getParameter("accionAdministracion");

        switch (accionAdministracion) {
            case "editarUsuario":
                this.editarUsuario(request, response);
                break;
            case "eliminarUsuario":
                this.eliminarUsuario(request, response);
                break;
            case "agregarUsuario":
                this.mostrarAgregarUsuario(request, response);
                break;
            case "editarMueble":
                this.mostrarEditarMueble(request, response);
                break;
            case "agregarMueble":
                this.mostrarAgregarMueble(request, response);
                break;
            case "agregarRequistoMueble":
                this.mostrarAgregarRequisitoMueble(request, response);
                break;
        }
    }

    private void mostrarEditarMueble(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombreMueble = request.getParameter("nombreMueble");

        Mueble mueble = new MuebleDao().encontrar(new Mueble(nombreMueble));
        request.setAttribute("mueble", mueble);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/editar-mueble.jsp").forward(request, response);
    }

    private void mostrarAgregarMueble(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/agregar-mueble.jsp").forward(request, response);
    }

    private void mostrarAgregarRequisitoMueble(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        List<Mueble> muebles = new MuebleDao().listar();
        List<TipoPieza> tipoPiezas = new TipoPiezaDao().listar();

        request.setAttribute("muebles", muebles);
        request.setAttribute("tipoPiezas", tipoPiezas);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/agregar-requerimiento-mueble.jsp").forward(request, response);
    }

    private void mostrarAgregarUsuario(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        List<String> areas = new ArrayList<>();
        areas = new AreaDao().listarAreas();

        request.setAttribute("areas", areas);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/agregar-usuario.jsp").forward(request, response);
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        Usuario usuario = new UsuarioDao().encontrar(new Usuario(nombreUsuario));

        List<String> areas = new ArrayList<>();
        areas = new AreaDao().listarAreas();

        request.setAttribute("usuario", usuario);
        request.setAttribute("areas", areas);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/editar-usuario.jsp").forward(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        new UsuarioDao().deshabilitar(nombreUsuario);
        this.mostrarUsuarios(request, response);
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String area = request.getParameter("area");

        if (nombreUsuario.isBlank()) {
            throw new MisExcepciones("Ingresa un nombre de usuario valido");
        }

        if (password.isBlank()) {
            throw new MisExcepciones("Ingresa una contrasena valida");
        }

        UsuarioDao usuarioDao = new UsuarioDao();
        if (usuarioDao.existe(nombreUsuario)) {
            throw new MisExcepciones("Este nombre de usuario ya esta en uso");
        }

        int idArea = new AreaDao().obtenerId(area);

        usuarioDao.insertar(new Usuario(nombreUsuario, password, idArea));

        this.mostrarUsuarios(request, response);
    }

    public void administracionPaginas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String paginasAdministracion = request.getParameter("paginaAdministracion");

        if (paginasAdministracion == null) {
            paginasAdministracion = "inicio";
        }

        try {
            switch (paginasAdministracion) {
                case "inicio":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/administracion.jsp").forward(request, response);
                    break;
                case "creacionMuebles":
                    this.mostrarMuebles(request, response);
                    break;
                case "manejoUsuarios":
                    this.mostrarUsuarios(request, response);
                    break;
                case "cargarDatos":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/cargar-datos.jsp").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    private void mostrarMuebles(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        List<Mueble> muebles = new MuebleDao().listar();
        request.setAttribute("muebles", muebles);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/creacion-muebles.jsp").forward(request, response);
    }

    private void mostrarUsuarios(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        List<Usuario> usuarios = new UsuarioDao().listar();

        for (Usuario u : usuarios) {
            int idArea = u.getIdArea();
            String area = new AreaDao().obtenerNombreArea(idArea);
            u.setArea(area);
        }

        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/manejo-usuarios.jsp").forward(request, response);
    }

    public void administracionAccionesPost(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String accionAdministracion = request.getParameter("accionAdministracion");
        try {
            switch (accionAdministracion) {
                case "reporteVentas":
                    this.reporteVentas(request, response);
                    break;
                case "reporteDevoluciones":
                    this.reporteDevoluciones(request, response);
                    break;
                case "reporteGanancias":
                    this.reporteGanancias(request, response);
                    break;
                case "reporteUsuarioMasVentas":
                    this.reporteUsuarioMasVentas(request, response);
                    break;
                case "reporteUsuarioMasGanancias":
                    this.reporteUsuarioMasGanancias(request, response);
                    break;
                case "reporteMuebleMasVendido":
                    this.reporteMuebleMasVendido(request, response);
                    break;
                case "reporteMuebleMenosVendido":
                    this.reporteMuebleMenosVendido(request, response);
                    break;
                case "modificarUsuario":
                    this.modificarUsuario(request, response);
                    break;
                case "crearUsuario":
                    this.agregarUsuario(request, response);
                case "editarMueble":
                    this.editarMueble(request, response);
                case "agregarMueble":
                    this.agregarMueble(request, response);
                case "agregarRequerimiento":
                    this.agregarRequerimiento(request, response);
            }
        } catch (ServletException | IOException ex) {
            ex.printStackTrace(System.out);
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    private void agregarRequerimiento(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String mueble = request.getParameter("mueble");
        String tipoPieza = request.getParameter("tipoPieza");
        int cantidad = 0;

        try {
            cantidad = Integer.valueOf(request.getParameter("cantidad"));
        } catch (NumberFormatException ex) {
            throw new MisExcepciones("Ingresa una cantidad valida");
        }

        if (cantidad < 1) {
            throw new MisExcepciones("Ingresa una cantidad valida");
        }

        EnsamblePiezaDao ensamblePiezadao = new EnsamblePiezaDao();
        if (ensamblePiezadao.existe(mueble, tipoPieza)) {
            ensamblePiezadao.sobrescribirCantidadRequerimiento(cantidad, mueble, tipoPieza);
        } else {
            TipoPieza tPieza = new TipoPiezaDao().encontrar(new TipoPieza(tipoPieza));
            int idPieza = tPieza.getIdTipoPieza();
            new EnsamblePiezaDao().insertar(new EnsamblePieza(mueble, idPieza, cantidad));
        }

        this.mostrarMuebles(request, response);
    }

    private void agregarMueble(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombreNuevoMueble = request.getParameter("nombreNuevoMueble");
        double precio = Double.valueOf(request.getParameter("precio"));

        if (nombreNuevoMueble.isBlank()) {
            throw new MisExcepciones("Por favor ingresa un nombre valido");
        }

        if (new MuebleDao().existe(nombreNuevoMueble)) {
            throw new MisExcepciones("Este mueble ya existe");
        }

        if (precio < 0) {
            throw new MisExcepciones("Por favor ingresa un precio valido");
        }

        new MuebleDao().insertar(new Mueble(nombreNuevoMueble, precio));

        this.mostrarMuebles(request, response);
    }

    private void editarMueble(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nombreMueble = request.getParameter("nombreMueble");
        double precio = Double.valueOf(request.getParameter("precio"));

        if (precio < 0) {
            throw new MisExcepciones("Por favor ingresa un precio valido");
        }

        new MuebleDao().actualizar(new Mueble(nombreMueble, precio));

        this.mostrarMuebles(request, response);
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String usuario = request.getParameter("nombreUsuario");
        String nuevaArea = request.getParameter("area");

        int idArea = new AreaDao().obtenerId(nuevaArea);

        new UsuarioDao().actualizar(idArea, usuario);

        this.mostrarUsuarios(request, response);
    }

    public void administracionConsultasGet(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String consultasAdministracion = request.getParameter("consultasAdministracion");

        try {
            switch (consultasAdministracion) {
                case "reporteVentas":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ventas.jsp").forward(request, response);
                    break;
                case "reporteDevoluciones":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-devoluciones.jsp").forward(request, response);
                    break;
                case "reporteGanancias":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ganancias.jsp").forward(request, response);
                    break;
                case "reporteUsuarioMasVentas":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ventas.jsp").forward(request, response);
                    break;
                case "reporteUsuarioMasGanancias":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ganancias.jsp").forward(request, response);
                    break;
                case "reporteMuebleMasVendido":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-mas-vendido.jsp").forward(request, response);
                    break;
                case "reporteMuebleMenosVendido":
                    request.setAttribute("fechaInicial", LocalDate.now().toString());
                    request.setAttribute("fechaFinal", LocalDate.now().toString());
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-menos-vendido.jsp").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    private void reporteVentas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteVentas(fechaInicial, fechaFinal);

        double totalVendido = 0;
        for (ConsultasAdministracion r : registros) {
            totalVendido += r.getPrecioVenta();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.setAttribute("cantidadVentas", registros.size());
        request.setAttribute("cantidadTotalVentas", df.format(totalVendido));
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ventas.jsp").forward(request, response);
    }

    private void reporteDevoluciones(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteDevoluciones(fechaInicial, fechaFinal);

        int numDevoluciones = registros.size();

        double totalPerdida = 0;
        for (ConsultasAdministracion r : registros) {
            totalPerdida += r.getPerdida();
        }
        
        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.setAttribute("numDevoluciones", numDevoluciones);
        request.setAttribute("totalPerdida", df.format(totalPerdida));
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-devoluciones.jsp").forward(request, response);
    }

    private void reporteGanancias(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteGanancias(fechaInicial, fechaFinal);

        double gananciaTotal = 0;
        for (ConsultasAdministracion r : registros) {
            gananciaTotal += r.getGanancia();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("cantidadVentas", registros.size());
        request.setAttribute("gananciaTotal", df.format(gananciaTotal));
        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ganancias.jsp").forward(request, response);
    }

    private void reporteUsuarioMasVentas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String vendedor = new ConsultasAdministracionDao().obtenerUsarioConMasVentas(fechaInicial, fechaFinal);
        if (vendedor == null) {
            throw new MisExcepciones("Aun no se han registrado ventas entre este intervalo");
        }
        registros = new ConsultasAdministracionDao().obtenerReporteUsarioConMasVentas(fechaInicial, fechaFinal, vendedor);

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("vendedor", vendedor);
        request.setAttribute("totalVentas", registros.size());
        request.setAttribute("registros", registros);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ventas.jsp").forward(request, response);
    }

    private void reporteUsuarioMasGanancias(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String vendedor = new ConsultasAdministracionDao().obtenerUsuarioConMasGanancias(fechaInicial, fechaFinal);

        if (vendedor == null) {
            throw new MisExcepciones("Aun no se han registrado ventas entre este intervalo");
        }

        registros = new ConsultasAdministracionDao().obtenerReporteUsarioConMasGanancias(fechaInicial, fechaFinal, vendedor);

        double totalGanancias = 0;
        for (ConsultasAdministracion g : registros) {
            totalGanancias += g.getGanancia();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("vendedor", vendedor);
        request.setAttribute("totalGanancias", df.format(totalGanancias));
        request.setAttribute("registros", registros);

        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ganancias.jsp").forward(request, response);
    }

    private void reporteMuebleMasVendido(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String nombreMueble = new ConsultasAdministracionDao().obtenerMuebleMasVendido(fechaInicial, fechaFinal);

        if (nombreMueble == null) {
            throw new MisExcepciones("Aun no se han registrado ventas entre este intervalo");
        }

        registros = new ConsultasAdministracionDao().reporteMuebleMasVendido(fechaInicial, fechaFinal, nombreMueble);

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("nombreMueble", nombreMueble);
        request.setAttribute("unidadesVendidas", registros.size());
        request.setAttribute("registros", registros);

        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-mas-vendido.jsp").forward(request, response);
    }

    private void reporteMuebleMenosVendido(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String nombreMueble = new ConsultasAdministracionDao().obtenerMuebleMenosVendido(fechaInicial, fechaFinal);

        if (nombreMueble == null) {
            throw new MisExcepciones("Aun no se han registrado ventas entre este intervalo");
        }

        registros = new ConsultasAdministracionDao().reporteMuebleMenosVendido(fechaInicial, fechaFinal, nombreMueble);

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("nombreMueble", nombreMueble);
        request.setAttribute("unidadesVendidas", registros.size());
        request.setAttribute("registros", registros);

        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-menos-vendido.jsp").forward(request, response);
    }

}
