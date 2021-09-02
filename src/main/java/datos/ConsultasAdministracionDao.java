package datos;

import dominio.cargarDatos.Funciones;
import dominio.cargarDatos.MisExcepciones;
import dominio.clases.ConsultasAdministracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasAdministracionDao {

    private static final String SQL_REPORTE_VENTAS = "SELECT f.fecha, e.tipo_mueble, d.precio, f.vendedor, f.num_factura FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN factura f ON(d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? ORDER BY f.fecha DESC";
    private static final String SQL_REPORTE_DEVOLUCIONES = "SELECT f.num_factura,f.fecha,d.fecha,c.nombre,f.vendedor,e.tipo_mueble,d.perdida FROM devolucion d JOIN ensamble e ON(d.id_mueble_devuelto=e.id_ensamble) JOIN factura f ON(d.num_factura=f.num_factura) JOIN cliente c ON(f.nit_cliente=c.nit) WHERE d.fecha BETWEEN ? AND ? ORDER BY d.fecha DESC";
    private static final String SQL_REPORTE_GANANCIAS = "SELECT f.fecha, e.tipo_mueble,d.precio, e.costo, d.precio-e.costo AS ganancia FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN factura f ON(d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? ORDER BY f.fecha DESC";
    private static final String SQL_USUARIO_MAS_VENTAS = "SELECT f.vendedor, COUNT(f.vendedor) AS total FROM detalle d JOIN factura f ON(d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? GROUP BY f.vendedor ORDER BY total DESC LIMIT 1";
    private static final String SQL_REPORTE_USUARIO_MAS_VENTAS = "SELECT f.fecha, e.tipo_mueble, d.precio FROM detalle d JOIN factura f ON(d.num_factura=f.num_factura) JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) WHERE f.fecha BETWEEN ? AND ? AND f.vendedor=?";
    private static final String SQL_USUARIO_MAS_GANANCIAS = "SELECT SUM(d.precio-e.costo) AS ganancias, COUNT(f.vendedor), f.vendedor FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN factura f ON(d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? GROUP BY f.vendedor ORDER BY ganancias DESC LIMIT 1";
    private static final String SQL_REPORTE_USUARIO_MAS_GANACIAS = "SELECT f.fecha, e.tipo_mueble, d.precio-e.costo AS ganancia FROM detalle d JOIN factura f ON(d.num_factura=f.num_factura) JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) WHERE f.fecha BETWEEN ? AND ? AND f.vendedor=?";
    private static final String SQL_MUEBLE_MAS_VENDIDO = "SELECT e.tipo_mueble, COUNT(e.tipo_mueble) AS total FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN factura f ON (d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? GROUP BY e.tipo_mueble ORDER BY total DESC LIMIT 1";
    private static final String SQL_REPORTE_MUEBLE_MAS_VENDIDO = "SELECT f.fecha, d.num_factura, d.id_ensamble, e.tipo_mueble, d.precio FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN factura f ON(d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? AND e.tipo_mueble=? ORDER BY f.fecha DESC";
    private static final String SQL_MUEBLE_MENOS_VENDIDO = "SELECT e.tipo_mueble, COUNT(e.tipo_mueble) AS total FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN factura f ON (d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? GROUP BY e.tipo_mueble ORDER BY total ASC LIMIT 1";
    private static final String SQL_REPORTE_MUEBLE_MENOS_VENDIDO = "SELECT f.fecha, d.num_factura, d.id_ensamble, e.tipo_mueble, d.precio FROM detalle d JOIN ensamble e ON(d.id_ensamble=e.id_ensamble) JOIN factura f ON(d.num_factura=f.num_factura) WHERE f.fecha BETWEEN ? AND ? AND e.tipo_mueble=? ORDER BY f.fecha DESC";

    public List<ConsultasAdministracion> obtenerReporteVentas(String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConsultasAdministracion> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REPORTE_VENTAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaVenta = rs.getString("f.fecha");
                String nombreProducto = rs.getString("e.tipo_mueble");
                double precioVenta = rs.getDouble("d.precio");
                String vendedor = rs.getString("f.vendedor");
                int numFactura = rs.getInt("f.num_factura");

                ConsultasAdministracion registro = new ConsultasAdministracion(Funciones.formatearFechaEnAEs(fechaVenta), nombreProducto, precioVenta, vendedor, numFactura);
                registros.add(registro);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }

    public List<ConsultasAdministracion> obtenerReporteDevoluciones(String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConsultasAdministracion> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REPORTE_DEVOLUCIONES);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numFactura = rs.getInt("f.num_factura");
                String fechaVenta = rs.getString("f.fecha");
                String fechaDevolucion = rs.getString("d.fecha");
                String nombreCliente = rs.getString("c.nombre");
                String vendedor = rs.getString("f.vendedor");
                String nombreProducto = rs.getString("e.tipo_mueble");
                double perdida = rs.getDouble("d.perdida");

                ConsultasAdministracion registro = new ConsultasAdministracion(Funciones.formatearFechaEnAEs(fechaVenta), nombreProducto, numFactura, Funciones.formatearFechaEnAEs(fechaDevolucion), nombreCliente, vendedor, perdida);
                registros.add(registro);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }

    public List<ConsultasAdministracion> obtenerReporteGanancias(String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConsultasAdministracion> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REPORTE_GANANCIAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaVenta = rs.getString("f.fecha");
                String nombreProducto = rs.getString("e.tipo_mueble");
                double precio = rs.getDouble("d.precio");
                double costo = rs.getDouble("e.costo");
                double ganancia = rs.getDouble("ganancia");

                ConsultasAdministracion registro = new ConsultasAdministracion(Funciones.formatearFechaEnAEs(fechaVenta), nombreProducto, precio, costo, ganancia);
                registros.add(registro);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }

    public String obtenerUsarioConMasVentas(String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String nombreUsuario = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_USUARIO_MAS_VENTAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombreUsuario = rs.getString("f.vendedor");
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return nombreUsuario;
    }

    public List<ConsultasAdministracion> obtenerReporteUsarioConMasVentas(String fechaInicial, String fechaFinal, String usuario) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConsultasAdministracion> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REPORTE_USUARIO_MAS_VENTAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, usuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaVenta = rs.getString("f.fecha");
                String nombreProducto = rs.getString("e.tipo_mueble");
                double precioVenta = rs.getDouble("d.precio");

                ConsultasAdministracion registro = new ConsultasAdministracion(Funciones.formatearFechaEnAEs(fechaVenta), nombreProducto, precioVenta);
                registros.add(registro);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }

    public String obtenerUsuarioConMasGanancias(String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nombreUsuario = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_USUARIO_MAS_GANANCIAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombreUsuario = rs.getString("f.vendedor");
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return nombreUsuario;
    }

    public List<ConsultasAdministracion> obtenerReporteUsarioConMasGanancias(String fechaInicial, String fechaFinal, String usuario) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConsultasAdministracion> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REPORTE_USUARIO_MAS_GANACIAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, usuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaVenta = rs.getString("f.fecha");
                String nombreProducto = rs.getString("e.tipo_mueble");
                double ganancia = rs.getDouble("ganancia");

                ConsultasAdministracion registro = new ConsultasAdministracion();
                registro.setFechaVenta(Funciones.formatearFechaEnAEs(fechaVenta));
                registro.setNombreProducto(nombreProducto);
                registro.setGanancia(ganancia);

                registros.add(registro);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }

    public String obtenerMuebleMasVendido(String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String nombreMueble = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_MUEBLE_MAS_VENDIDO);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombreMueble = rs.getString("e.tipo_mueble");
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return nombreMueble;
    }

    public List<ConsultasAdministracion> reporteMuebleMasVendido(String fechaInicial, String fechaFinal, String mueble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConsultasAdministracion> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REPORTE_MUEBLE_MAS_VENDIDO);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, mueble);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaVenta = rs.getString("f.fecha");
                int numFactura = rs.getInt("d.num_factura");
                int idEnsamble = rs.getInt("d.id_ensamble");
                double precio = rs.getDouble("d.precio");

                ConsultasAdministracion registro = new ConsultasAdministracion(Funciones.formatearFechaEnAEs(fechaVenta), precio, numFactura, idEnsamble);
                registros.add(registro);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }

    public String obtenerMuebleMenosVendido(String fechaInicial, String fechaFinal) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String nombreMueble = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_MUEBLE_MENOS_VENDIDO);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombreMueble = rs.getString("e.tipo_mueble");
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return nombreMueble;
    }

    public List<ConsultasAdministracion> reporteMuebleMenosVendido(String fechaInicial, String fechaFinal, String mueble) throws MisExcepciones {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConsultasAdministracion> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REPORTE_MUEBLE_MENOS_VENDIDO);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, mueble);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaVenta = rs.getString("f.fecha");
                int numFactura = rs.getInt("d.num_factura");
                int idEnsamble = rs.getInt("d.id_ensamble");
                double precio = rs.getDouble("d.precio");

                ConsultasAdministracion registro = new ConsultasAdministracion(Funciones.formatearFechaEnAEs(fechaVenta), precio, numFactura, idEnsamble);
                registros.add(registro);
            }

        } catch (SQLException ex) {
            throw new MisExcepciones("Algo salio mal al ejecutar la declaracion hacia la base de datos");
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }
}
