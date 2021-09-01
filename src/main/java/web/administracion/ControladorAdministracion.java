package web.administracion;

import dominio.cargarDatos.MisExcepciones;
import java.io.IOException;
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

    public void administracionAccionesPost(HttpServletRequest request, HttpServletResponse response) {

    }

    public void administracionConsultasGet(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String consultasAdministracion = request.getParameter("consultasAdministracion");

        try {
            switch (consultasAdministracion) {
                case "reporteVentas":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ventas.jsp").forward(request, response);
                    break;
                case "reporteDevoluciones":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-devoluciones.jsp").forward(request, response);
                    break;
                case "reporteGanancias":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-ganancias.jsp").forward(request, response);
                    break;
                case "reporteUsuarioMasVentas":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ventas.jsp").forward(request, response);
                    break;
                case "reporteUsuarioMasGanancias":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-usuario-mas-ganancias.jsp").forward(request, response);
                    break;
                case "reporteMuebleMasVendido":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-mas-vendido.jsp").forward(request, response);
                    break;
                case "reporteMuebleMenosVendido":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/reporte-mueble-menos-vendido.jsp").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

}
