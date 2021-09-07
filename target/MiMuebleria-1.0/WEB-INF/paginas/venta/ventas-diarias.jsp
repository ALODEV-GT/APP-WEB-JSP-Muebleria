<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<div class="container col-md-8 my-5">
    <form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=buscarVentasDia" method="POST">
        <div class="d-flex align-items-start">
            <div>
        <h1>VENTAS DIARIAS</h1>
            </div>
            <div style="padding: 8px"></div>
            <div class="align-self-center">
        <label>Fecha</label>
        <input type="date" value="${fechaHoy}"  name="dia"/>
        <input type="submit"   value="Mostrar"/>
            </div>
        </div>
    </form>
    <br>
    <table class="table">
        <thead class="table-dark">
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
                <td colspan="3"><b>Total</b></td>
                <td style="font-size: 18px"><strong>${total}</strong> </td>
            </tr>
        </tbody>
    </table>
    <jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>