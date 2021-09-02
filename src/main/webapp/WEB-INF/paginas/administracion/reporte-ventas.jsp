<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>REPORTE VENTAS</h1>

<form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=reporteVentas" method="POST">
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial" value="${fechaInicial}" />
    <label>Fecha final</label>
    <input type="date" name="fechaFinal" value="${fechaFinal}" />
    <input type="submit"  value="Mostrar"/>
</form>
<div>
    <a href="${pageContext.request.contextPath}/ServletExportar?reporte=ventas&fechaInicial=${fechaInicial}&fechaFinal=${fechaFinal}">EXPORTAR REPORTE</a>
</div>

<table>
    <tr>
        <td>CANTIDAD DE VENTAS</td>
        <td>${cantidadVentas}</td>
    </tr>
    <tr>
        <td>TOTAL VENDIDO</td>
        <td>${cantidadTotalVentas}</td>
    </tr>
</table>

<table>
    <thead>
        <tr>
            <th>FECHA DE VENTA</th>
            <th>NO. FACTURA</th>
            <th>VENDEDOR</th>
            <th>PRODUCTO</th>
            <th>PRECIO DE VENTA</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="registros" items="${registros}">
            <tr>
                <td>${registros.fechaVenta}</td>
                <td>${registros.numFactura}</td>
                <td>${registros.usuarioVendedor}</td>
                <td>${registros.nombreProducto}</td>
                <td>${registros.precioVenta}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>


<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>