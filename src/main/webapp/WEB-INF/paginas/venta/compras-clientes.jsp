<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<h3> COMPRAS CLIENTES </h3>

<form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=comprasClientes" method="POST">
    <label>Nit</label>
    <input type="text" name="nitCliente" value="${nitCliente}"/>
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial" value="${fechaInicial}"/>
    <label>Fecha final</label>
    <input type="date" name="fechaFinal" value="${fechaFinal}"/>
    <input type="submit"  value="Mostrar"/>
</form>


<table>
    <thead>
        <tr>
            <th>Fecha</th>
            <th>Nombre del cliente </th>
            <th># Factura</th>
            <th>Id. Producto </th>
            <th>Producto</th>
            <th>Precio</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="facturas" items="${facturas}">
            <c:forEach var="detalles" items="${facturas.detalles}">
            <tr>
                <td>${facturas.fecha}</td>
                <td>${facturas.nombreCliente}</td>
                <td>${facturas.numFactura}</td>
                <td>${detalles.idEnsamble}</td>
                <td>${detalles.nombreProducto}</td>
                <td>${detalles.precio}</td>
            </tr>
            </c:forEach>
        </c:forEach>
            <tr><td colspan="5">Total</td><td>${total}</td></tr>
    </tbody>
</table>



<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>