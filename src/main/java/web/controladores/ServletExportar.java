package web.controladores;

import datos.ConsultasAdministracionDao;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.ConsultasAdministracion;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletExportar")
public class ServletExportar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reporte = request.getParameter("reporte");

        try {
            switch (reporte) {
                case "ventas":
                    this.generReporteVentas(request, response);
                    break;
                case "devoluciones":
                    this.generarReporteDevoluciones(request, response);
                    break;
                case "ganancias":
                    this.generarReporteGanancias(request, response);
                    break;
                case "reporteUsuarioMasVentas":
                    this.generarReporteUsuarioMasVentas(request, response);
                    break;
                case "reporteUsuarioMasGanancias":
                    this.generarReporteUsuarioMasGanancias(request, response);
                    break;
                case "reporteMuebleMasVendido":
                    this.generarReporteMuebleMasVendido(request, response);
                    break;
                case "reporteMuebleMenosVendido":
                    this.generarReporteMuebleMenosVendido(request, response);
                    break;
            }
        } catch (MisExcepciones ex) {
            ex.printStackTrace(System.out);
        }

    }

    private void generReporteVentas(HttpServletRequest request, HttpServletResponse response) throws IOException, MisExcepciones {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteVentas" + fechaInicial + "A" + fechaFinal + ".csv");
        response.setDateHeader("Expires", -1);
        PrintWriter out = response.getWriter();

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteVentas(fechaInicial, fechaFinal);

        double totalVendido = 0;
        for (ConsultasAdministracion r : registros) {
            totalVendido += r.getPrecioVenta();
        }

        String contenido = "CANTIDAD DE VENTAS," + registros.size()
                + "\nTOTAL VENDIDO," + totalVendido + "\nFECHA DE VENTA,NO. FACTURA,VENDEDOR,PRODUCTO,PRECIO DE VENTA\n";

        for (ConsultasAdministracion r : registros) {
            contenido += r.getFechaVenta() + ",";
            contenido += r.getNumFactura() + ",";
            contenido += r.getUsuarioVendedor() + ",";
            contenido += r.getNombreProducto() + ",";
            contenido += r.getPrecioVenta() + "\n";
        }

        out.println(contenido);
    }

    private void generarReporteDevoluciones(HttpServletRequest request, HttpServletResponse response) throws IOException, MisExcepciones {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteDevoluciones" + fechaInicial + "A" + fechaFinal + ".csv");
        response.setDateHeader("Expires", -1);
        PrintWriter out = response.getWriter();

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteDevoluciones(fechaInicial, fechaFinal);

        int numDevoluciones = registros.size();

        double totalPerdida = 0;
        for (ConsultasAdministracion r : registros) {
            totalPerdida += r.getPerdida();
        }

        String contenido = "NUMERO DE DEVOLUCIONES," + numDevoluciones
                + "\nTOTAL DE PERDIDA," + totalPerdida + "\nNO. FACTURA,FECHA DE VENTA,FECHA DE DEVOLUCION,NOMBRE DEL CLIENTE,VENDEDOR,PRODUCTO,PERDIDA\n";

        for (ConsultasAdministracion r : registros) {
            contenido += r.getNumFactura() + ",";
            contenido += r.getFechaVenta() + ",";
            contenido += r.getFechaDevolucion() + ",";
            contenido += r.getNombreCliente() + ",";
            contenido += r.getUsuarioVendedor() + ",";
            contenido += r.getNombreProducto() + ",";
            contenido += r.getPerdida() + "\n";
        }

        out.println(contenido);
    }

    private void generarReporteGanancias(HttpServletRequest request, HttpServletResponse response) throws IOException, MisExcepciones {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteGanancias" + fechaInicial + "A" + fechaFinal + ".csv");
        response.setDateHeader("Expires", -1);
        PrintWriter out = response.getWriter();

        List<ConsultasAdministracion> registros = new ArrayList<>();
        registros = new ConsultasAdministracionDao().obtenerReporteGanancias(fechaInicial, fechaFinal);

        double gananciaTotal = 0;
        for (ConsultasAdministracion r : registros) {
            gananciaTotal += r.getGanancia();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        String contenido = "CANTIDAD DE VENTAS," + registros.size()
                + "\nGANANCIA TOTAL," + df.format(gananciaTotal) + "\nFECHA DE VENTA,PRODUCTO,PRECIO DE VENTA,COSTO DE FABRICACION,GANANCIA\n";

        for (ConsultasAdministracion r : registros) {
            contenido += r.getFechaVenta() + ",";
            contenido += r.getNombreProducto() + ",";
            contenido += r.getPrecioVenta() + ",";
            contenido += r.getCosto() + ",";
            contenido += r.getGanancia() + "\n";
        }

        out.println(contenido);
    }

    private void generarReporteUsuarioMasVentas(HttpServletRequest request, HttpServletResponse response) throws IOException, MisExcepciones {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteUsuarioMasVentas" + fechaInicial + "A" + fechaFinal + ".csv");
        response.setDateHeader("Expires", -1);
        PrintWriter out = response.getWriter();

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String vendedor = new ConsultasAdministracionDao().obtenerUsarioConMasVentas(fechaInicial, fechaFinal);

        if (vendedor == null) {
            vendedor = "";
        } else {
            registros = new ConsultasAdministracionDao().obtenerReporteUsarioConMasVentas(fechaInicial, fechaFinal, vendedor);
        }

        String contenido = "USUARIO," + vendedor
                + "\nTOTAL DE VENTAS," + registros.size() + "\nFECHA DE VENTA,PRODUCTO,PRECIO DE VENTA\n";

        for (ConsultasAdministracion r : registros) {
            contenido += r.getFechaVenta() + ",";
            contenido += r.getNombreProducto() + ",";
            contenido += r.getPrecioVenta() + "\n";
        }

        out.println(contenido);
    }

    private void generarReporteUsuarioMasGanancias(HttpServletRequest request, HttpServletResponse response) throws IOException, MisExcepciones {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteUsuarioMasGanancias" + fechaInicial + "A" + fechaFinal + ".csv");
        response.setDateHeader("Expires", -1);
        PrintWriter out = response.getWriter();

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String vendedor = new ConsultasAdministracionDao().obtenerUsuarioConMasGanancias(fechaInicial, fechaFinal);

        if (vendedor == null) {
            vendedor = "";
        } else {
            registros = new ConsultasAdministracionDao().obtenerReporteUsarioConMasGanancias(fechaInicial, fechaFinal, vendedor);
        }

        double totalGanancias = 0;
        for (ConsultasAdministracion g : registros) {
            totalGanancias += g.getGanancia();
        }

        DecimalFormat df = new DecimalFormat("#.00");

        String contenido = "USUARIO," + vendedor
                + "\nGANANCIA TOTAL," + df.format(totalGanancias) + "\nFECHA DE VENTA,PRODUCTO,GANANCIA\n";

        for (ConsultasAdministracion r : registros) {
            contenido += r.getFechaVenta() + ",";
            contenido += r.getNombreProducto() + ",";
            contenido += r.getGanancia() + "\n";
        }

        out.println(contenido);
    }

    private void generarReporteMuebleMasVendido(HttpServletRequest request, HttpServletResponse response) throws IOException, MisExcepciones {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteMuebleMasVendido" + fechaInicial + "A" + fechaFinal + ".csv");
        response.setDateHeader("Expires", -1);
        PrintWriter out = response.getWriter();

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String nombreMueble = new ConsultasAdministracionDao().obtenerMuebleMasVendido(fechaInicial, fechaFinal);

        if (nombreMueble == null) {
            nombreMueble = "";
        } else {
            registros = new ConsultasAdministracionDao().reporteMuebleMasVendido(fechaInicial, fechaFinal, nombreMueble);
        }

        String contenido = "MUEBLE," + nombreMueble
                + "\nUNIDADES VENDIDAS," + registros.size() + "\nFECHA DE VENTA,NO. FACTURA,ID. PRODUCTO,PRECIO DE VENTA\n";

        for (ConsultasAdministracion r : registros) {
            contenido += r.getFechaVenta() + ",";
            contenido += r.getNumFactura() + ",";
            contenido += r.getIdEnsamble() + ",";
            contenido += r.getPrecioVenta() + "\n";
        }

        out.println(contenido);
    }

    private void generarReporteMuebleMenosVendido(HttpServletRequest request, HttpServletResponse response) throws IOException, MisExcepciones {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=ReporteMuebleMenosVendido" + fechaInicial + "A" + fechaFinal + ".csv");
        response.setDateHeader("Expires", -1);
        PrintWriter out = response.getWriter();

        List<ConsultasAdministracion> registros = new ArrayList<>();
        String nombreMueble = new ConsultasAdministracionDao().obtenerMuebleMenosVendido(fechaInicial, fechaFinal);

        if (nombreMueble == null) {
            nombreMueble = "";
        } else {
            registros = new ConsultasAdministracionDao().reporteMuebleMenosVendido(fechaInicial, fechaFinal, nombreMueble);
        }

        String contenido = "MUEBLE," + nombreMueble
                + "\nUNIDADES VENDIDAS," + registros.size() + "\nFECHA DE VENTA,NO. FACTURA,ID. PRODUCTO,PRECIO DE VENTA\n";

        for (ConsultasAdministracion r : registros) {
            contenido += r.getFechaVenta() + ",";
            contenido += r.getNumFactura() + ",";
            contenido += r.getIdEnsamble() + ",";
            contenido += r.getPrecioVenta() + "\n";
        }

        out.println(contenido);
    }
}
