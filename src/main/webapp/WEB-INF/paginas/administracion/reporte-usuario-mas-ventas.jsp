<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>REPORTE USUARIO MAS VENTAS</h1>
<form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=reporteUsuarioMasVentas" method="POST">
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial" value="${fechaInicial}"/>
    <label>Fecha final</label>
    <input type="date" name="fechaFinal" value="${fechaFinal}"/>
    <input type="submit"  value="Mostrar"/>
</form>
<div>
    <a href="${pageContext.request.contextPath}/ServletExportar?reporte=reporteUsuarioMasVentas&fechaInicial=${fechaInicial}&fechaFinal=${fechaFinal}">EXPORTAR REPORTE</a>
</div>

<table>
    <tr>
        <td>USUARIO</td>
        <td>${vendedor}</td>
    </tr>
    <tr>
        <td>TOTAL DE VENTAS</td>
        <td>${totalVentas}</td>
    </tr>
</table>

<table>
    <thead>
        <tr>
            <th>FECHA DE VENTA</th>
            <th>PRODUCTO</th>
            <th>PRECIO DE VENTA</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="registros" items="${registros}">
            <tr>
                <td>${registros.fechaVenta}</td>
                <td>${registros.nombreProducto}</td>
                <td>${registros.precioVenta}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>