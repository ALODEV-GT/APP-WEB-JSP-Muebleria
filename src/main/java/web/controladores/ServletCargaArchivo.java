package web.controladores;

import dominio.cargarDatos.CargarDatos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ServletCargaArchivo")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ServletCargaArchivo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Recibe el archivo
        Part filePart = request.getPart("archivoCargaDatos");

        //Almacenamiento del archivo
        String nombreArchivo = filePart.getSubmittedFileName();
        String path = this.getServletConfig().getServletContext().getRealPath("/archivo");
        File directorio = new File(path);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        filePart.write(path + "/" + nombreArchivo);

        //Procesamiento del archivo
        File archivo = new File(path + "/" + nombreArchivo);
        CargarDatos cargarDatos = new CargarDatos(archivo);

        List<String> errores = new ArrayList<>(Arrays.asList(cargarDatos.getErrores().split("%")));

        request.setAttribute("errores", errores);
        request.getRequestDispatcher("/WEB-INF/paginas/administracion/cargar-datos.jsp").forward(request, response);
    }
}
