<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>REPORTE USUARIO MAS GANANCIAS</h1>
<form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=reporteUsuarioMasGanancias" method="POST">
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial" value="${fechaInicial}"/>
    <label>Fecha final</label>
    <input type="date" name="fechaFinal" value="${fechaFinal}"/>
    <input type="submit"  value="Mostrar"/>
</form>
<div>
    <a href="${pageContext.request.contextPath}/ServletExportar?reporte=reporteUsuarioMasGanancias&fechaInicial=${fechaInicial}&fechaFinal=${fechaFinal}">EXPORTAR REPORTE</a>
</div>

<table>
    <tr>
        <td>USUARIO</td>
        <td>${vendedor}</td>
    </tr>
    <tr>
        <td>GANANCIA TOTAL</td>
        <td>${totalGanancias}</td>
    </tr>
</table>  

<table>
    <thead>
        <tr>
            <th>FECHA DE VENTA</th>
            <th>PRODUCTO</th>
            <th>GANANCIA</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="registros" items="${registros}">
            <tr>
                <td>${registros.fechaVenta}</td>
                <td>${registros.nombreProducto}</td>
                <td>${registros.ganancia}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>