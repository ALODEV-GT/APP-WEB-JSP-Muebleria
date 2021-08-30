<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<h1> DETALLE DE FACTURAS </h1>

<form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=detalleFacturas" method="POST">
    <label>Nit cliente</label>
    <input type="text" name="nitCliente"  /> 
    <label>Numero de factura</label>
    <input type="number" name="numFactura"/>
    <input type="submit"  value="Mostrar"/>
</form>

<br>
<br>
<br>
<c:forEach var="facturas" items="${facturas}">
    <table>
        <thead>
            <tr>
                <td>Numero de factura:</td>
                <td>${facturas.numFactura}</td>
            </tr>
            <tr>
                <td>Nombre del cliente:</td>
                <td>${facturas.nombreCliente}</td>
            </tr>
            <tr>
                <td>Nit:</td>
                <td>${facturas.nitCliente}</td>
            </tr>
            <tr>
                <td>Fecha:</td>
                <td>${facturas.fecha}</td>
            </tr>
            <tr>
                <td>Vendedor:</td>
                <td>${facturas.vendedor}</td>
            </tr>
        </thead>
    </table>
    <table border="1">
        <thead>
            <tr>
                <th># detalle </th>
                <th>Id. Producto</th>
                <th>Producto </th>
                <th>Precio</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="detalles" items="${facturas.detalles}">
            <tr>
                <td>${detalles.numDetalle}</td>
                <td>${detalles.idEnsamble}</td>
                <td>${detalles.nombreProducto}</td>
                <td>${detalles.precio}</td>
            </tr>
            </c:forEach>
            <tr>
                <td colspan="2">Total<td>
                <td>${facturas.total}</td>
            </tr>        
        </tbody>
    </table>
    <br>
    <br>
</c:forEach>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>