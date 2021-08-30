<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<h1> VENTAS DIARIAS </h1>

<form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=buscarVentasDia" method="POST">
    <label>Fecha</label>
    <input type="date" value="${fechaHoy}"  name="dia"/>
    <input type="submit"  value="Mostrar"/>
</form>



<table>
    <thead>
        <tr>
            <th># Factura </th>
            <th>Id. Producto</th>
            <th>Producto </th>
            <th>Precio </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="detalles" items="${detalles}">
            <tr>
                <td>${detalles.numFactura}</td>
                <td>${detalles.idEnsamble}</td>
                <td>${detalles.nombreProducto}</td>
                <td>${detalles.precio}</td>
            </tr>
        </c:forEach>
            <tr>
                <td colspan="3">Total</td>
                <td>${total}</td>
            </tr>
    </tbody>
</table>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>