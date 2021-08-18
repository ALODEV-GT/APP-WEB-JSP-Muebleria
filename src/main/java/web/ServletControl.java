package web;

import datos.UsuarioDao;
import dominio.Usuario;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletControlador")
public class ServletControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("publicas/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "validarUsuario":
                    this.validarUsuario(request, response);
                    break;
                case "cargarDatos":
                    this.cargarDatos(request, response);
                    break;
                default:
                    response.sendRedirect("publicas/login.jsp");
            }
        }
    }

    private void cargarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String passw = request.getParameter("password");
        String area;

        //Construccion de modelo
        Usuario usuario = new Usuario(nombreUsuario);

        //Buscamos si sus datos coinciden
        usuario = new UsuarioDao().encontrar(usuario);

        String mensaje = " ";

        if (usuario.getPassword().equals(passw)) {
            area = usuario.getArea();

            switch (area) {
                case "Administracion":
                    request.getRequestDispatcher("/WEB-INF/paginas/administracion/administracion.jsp").forward(request, response);
                    break;
                case "Fabrica":
                    request.getRequestDispatcher("/WEB-INF/paginas/fabrica/fabrica.jsp").forward(request, response);
                    break;
                case "Venta":
                    request.getRequestDispatcher("/WEB-INF/paginas/venta/ventas.jsp").forward(request, response);
                    break;
            }

        } else {
            mensaje = "ESTE USUARIO NO EXISTE";
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("/publicas/login.jsp").forward(request, response);
        }

    }
}