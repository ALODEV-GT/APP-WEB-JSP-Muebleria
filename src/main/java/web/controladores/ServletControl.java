package web.controladores;

import datos.UsuarioDao;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.fabrica.ControladorFabrica;

@WebServlet("/ServletControlador")
public class ServletControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagina = request.getParameter("pagina");
        String accion = request.getParameter("accion");
        String accionFabrica = request.getParameter("accionFabrica");

        try {
            if (pagina == null && accion == null && accionFabrica == null) {
                response.sendRedirect("publicas/login.jsp");
            } else if (pagina != null) {
                ControladorFabrica controladorFabrica = new ControladorFabrica();
                controladorFabrica.fabricaPaginas(request, response);
            } else if (accion != null) {

                switch (accion) {
                    case "logOut":
                        this.logOut(request, response);
                        break;
                }

            } else if (accionFabrica != null) {
                ControladorFabrica controladorFabrica = new ControladorFabrica();
                controladorFabrica.fabricaAccionesGET(request, response);
            }
        } catch (MisExcepciones ex) {
            System.err.println("Error: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String accionFabrica = request.getParameter("accionFabrica");
        try {
            if (accion != null) {
                switch (accion) {
                    case "validarUsuario":
                        this.validarUsuario(request, response);

                        break;
                    default:
                        response.sendRedirect("publicas/login.jsp");
                }
            } else if (accionFabrica != null) {
                ControladorFabrica controladorFabrica = new ControladorFabrica();
                controladorFabrica.fabricaAccionesPost(request, response);
            }
        } catch (MisExcepciones ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("/publicas/login.jsp").forward(request, response);
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MisExcepciones {
        HttpSession sesion = request.getSession(true);
        sesion.removeAttribute("nombreUsuario");
        sesion.removeAttribute("password");
        sesion.invalidate();
        response.sendRedirect("publicas/login.jsp");
    }

    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MisExcepciones {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String passw = request.getParameter("password");
        String area;

        //Construccion de modelo
        Usuario usuario = new Usuario(nombreUsuario);

        //Buscamos si sus datos coinciden
        UsuarioDao usuarioDao = new UsuarioDao();
        if (!usuarioDao.existe(nombreUsuario)) {
            throw new MisExcepciones("El usuario no existe");
        }

        usuario = new UsuarioDao().encontrar(usuario);

        if (!usuario.getPassword().equals(passw)) {
            throw new MisExcepciones("Contrasena incorrecta");
        }

        area = usuario.getArea();

        HttpSession sesion = request.getSession();
        sesion.setAttribute("nombreUsuario", nombreUsuario);
        sesion.setAttribute("area", area.toUpperCase());

        switch (area) {
            case "Financiero":
                request.getRequestDispatcher("/WEB-INF/paginas/administracion/administracion.jsp").forward(request, response);
                break;
            case "Fabrica": {
                ControladorFabrica controladorFabrica = new ControladorFabrica();
                controladorFabrica.fabricaPaginas(request, response);
            }
            break;

            case "Punto de venta":
                request.getRequestDispatcher("/WEB-INF/paginas/venta/ventas.jsp").forward(request, response);
                break;
        }

    }
}
