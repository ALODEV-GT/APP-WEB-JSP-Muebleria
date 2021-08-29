package web.fabrica;

import datos.ArmadoDao;
import datos.ClienteDao;
import datos.DetalleDao;
import datos.DevolucionDao;
import datos.EnsambleMuebleDao;
import datos.FacturaDao;
import datos.PiezaDao;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.Armado;
import dominio.clases.Cliente;
import dominio.clases.Detalle;
import dominio.clases.Devolucion;
import dominio.clases.EnsamblarMueble;
import dominio.clases.Factura;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorVentas {

    public void ventasPaginas(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String paginaVentas = request.getParameter("paginaVenta");

        if (paginaVentas == null) {
            paginaVentas = "inicio";
        }

        try {
            switch (paginaVentas) {
                case "inicio":
                    this.inicio(request, response);
                    break;
                case "consultasVenta":
                    this.consultasVenta(request, response);
                    break;
                case "productos":
                    this.productos(request, response);
                    break;
                case "devolverMueble":
                    request.getRequestDispatcher("/WEB-INF/paginas/venta/devolucion.jsp").forward(request, response);
                    break;
                default:

            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    public void ventasAccionesPost(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String accionVentas = request.getParameter("accionVentas");
        try {
            switch (accionVentas) {
                case "obtenerDatos":
                    this.obtenerDatos(request, response);
                    break;
                case "realizarCompra":
                    this.realizarCompra(request, response);
                    break;
                case "devolver":
                    this.devolverMueble(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            ex.printStackTrace(System.out);
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    public void ventasAccionesGET(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones {
        String accionVentas = request.getParameter("accionVentas");
        try {
            switch (accionVentas) {
                case "obtenerDatos":
                    this.obtenerDatos(request, response);
                    break;
                case "  ":
                    break;
                case " ":
                    break;
            }
        } catch (ServletException | IOException ex) {
            throw new MisExcepciones("Ocurrio un error al intentar mostrar la pagina");
        }
    }

    private void realizarCompra(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombreCliente");
        String direccion = request.getParameter("direccion");
        String municipio = request.getParameter("municipio");
        String departamento = request.getParameter("departamento");
        String mueblesAComprar = request.getParameter("mueblesAComprar");
        String vendedor = request.getParameter("vendedor");

        if (nit.isBlank() || nombre.isBlank()) {
            throw new MisExcepciones("Debes llenar los campos");
        }

        Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();
        if (clienteDao.existe(nit)) {
            clienteDao.encontrar(cliente);
        } else {
            direccion = (direccion.isBlank()) ? "ciudad" : direccion;
            if (municipio.isBlank() || departamento.isBlank()) {
                clienteDao.insertar(new Cliente(nombre, nit, direccion));
            } else {
                clienteDao.insertar(new Cliente(nombre, nit, direccion, municipio, departamento));
            }
        }

        Set<Integer> idsMuebles = this.obtenerIdMueblesAComprar(mueblesAComprar);

        EnsambleMuebleDao ensambleMuebleDao = new EnsambleMuebleDao();

        for (Integer id : idsMuebles) {
            if (!ensambleMuebleDao.estaEnVenta(id)) {
                throw new MisExcepciones("El mueble con el identificador " + id + " no existe");
            }
        }

        FacturaDao facturaDao = new FacturaDao();
        facturaDao.insertar(new Factura(nit, LocalDate.now().toString(), vendedor));
        int numFactura = new FacturaDao().obtenerNumFactura();

        DetalleDao detalleDao = new DetalleDao();

        int contador = 1;
        for (Integer id : idsMuebles) {
            double precio = new EnsambleMuebleDao().obtenerPrecio(id);
            ensambleMuebleDao.venderMueble(id);
            detalleDao.insertar(new Detalle(contador++, numFactura, id, precio));
        }

        this.inicio(request, response);
    }

    private Set<Integer> obtenerIdMueblesAComprar(String cadena) throws MisExcepciones {
        String limpia = cadena.replace(" ", "");
        String[] idMueblesS = limpia.split(",");

        Set<Integer> ids = new HashSet<>();
        for (String id : idMueblesS) {
            try {
                if (!id.isBlank()) {
                    ids.add(Integer.valueOf(id));
                }
            } catch (NumberFormatException ex) {
                throw new MisExcepciones("Ingresa el id del mueble a comprar, si es mas de uno separalos con una coma (,)");
            }
        }
        return ids;
    }

    private void obtenerDatos(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        String nit = request.getParameter("nit");

        ClienteDao clienteDao = new ClienteDao();
        if (clienteDao.existe(nit)) {
            Cliente cliente = new Cliente(nit);
            clienteDao.encontrar(cliente);
            HttpSession sesion = request.getSession();
            sesion.setAttribute("cliente", cliente);
            this.inicio(request, response);
        } else {
            Cliente cliente = new Cliente(nit);
            HttpSession sesion = request.getSession();
            sesion.setAttribute("cliente", cliente);
            this.inicio(request, response);
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/paginas/venta/ventas.jsp").forward(request, response);
    }

    private void consultasVenta(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/paginas/venta/consultas.jsp").forward(request, response);
    }

    private void productos(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        List<EnsamblarMueble> muebles = new EnsambleMuebleDao().listarDisponibles();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("productos", muebles);
        request.getRequestDispatcher("/WEB-INF/paginas/venta/productos.jsp").forward(request, response);
    }

    private void devolverMueble(HttpServletRequest request, HttpServletResponse response) throws MisExcepciones, ServletException, IOException {
        int numFactura = Integer.valueOf(request.getParameter("numFactura"));
        int idEnsamble = Integer.valueOf(request.getParameter("idMueble"));

        DetalleDao detalleDao = new DetalleDao();

        if (!detalleDao.existeCompra(numFactura, idEnsamble)) {
            throw new MisExcepciones("No existe registro sobre esta compra");
        }

        Factura factura = new FacturaDao().encontrar(numFactura);

        if (!this.sePuedeDevolver(factura.getFecha())) {
            throw new MisExcepciones("No se puede devolver la pieza, ya paso 1 semana");
        }

        EnsambleMuebleDao ensambleMuebleDao = new EnsambleMuebleDao();
        EnsamblarMueble ensamblarMueble = ensambleMuebleDao.encontrar(new EnsamblarMueble(idEnsamble));
        if (ensamblarMueble.getDevuelto() == 1) {
            throw new MisExcepciones("Este mueble ya fue devuelto");
        }

        ArmadoDao armadoDao = new ArmadoDao();
        List<Armado> armado = armadoDao.listarSegunIdEnsamble(idEnsamble);

        ensambleMuebleDao.devolver(idEnsamble);

        PiezaDao piezaDao = new PiezaDao();
        for (int i = 0; i < armado.size(); i++) {
            piezaDao.rehusarPieza(armado.get(i).getIdPiezaUsada());
        }

        DevolucionDao devolucionDao = new DevolucionDao();
        double perdida = ensamblarMueble.getCosto() * 0.3333;
        devolucionDao.insertar(new Devolucion(numFactura, LocalDate.now().toString(), perdida));

        request.getRequestDispatcher("/WEB-INF/paginas/venta/devolucion.jsp").forward(request, response);
    }

    private boolean sePuedeDevolver(String fechaFactura) {
        boolean aceptable = false;
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaFac = LocalDate.parse(fechaFactura);
        long semanas = fechaFac.until(fechaActual, ChronoUnit.WEEKS);
        if (semanas == 0) {
            aceptable = true;
        }
        return aceptable;
    }

}
