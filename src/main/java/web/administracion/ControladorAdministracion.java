package web.administracion;

import datos.ConsultasAdministracionDao;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.ConsultasAdministracion;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorAdministracion {

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
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/creacion-muebles.jsp").forward(request, response);
                    break;
                case "manejoUsuarios":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/manejo-usuarios.jsp").forward(request, response);
                    break;
                case "cargarDatos":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/cargar-datos.jsp").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
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
            }
        } catch (ServletException | IOException ex) {
            ex.printStackTrace(System.out);
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
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

        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.setAttribute("cantidadVentas", registros.size());
        request.setAttribute("cantidadTotalVentas", totalVendido);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ventas.jsp").forward(request, response);
    }

    private void reporteDevoluciones(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteDevoluciones(fechaInicial, fechaFinal);

        int numDevoluciones = registros.size();
        
        double totalPerdida = 0;
        for(ConsultasAdministracion r : registros){
            totalPerdida+=r.getPerdida();
        }
        
        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute("registros", registros);
        request.setAttribute("numDevoluciones", numDevoluciones);
        request.setAttribute("totalPerdida", totalPerdida);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-devoluciones.jsp").forward(request, response);
    }

    private void reporteGanancias(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteGanancias(fechaInicial, fechaFinal);

        double gananciaTotal = 0;
        for(ConsultasAdministracion r: registros){
            gananciaTotal+=r.getGanancia();
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
