<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>REPORTE MUEBLE MENOS VENDIDO</h1>
<form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=reporteMuebleMenosVendido" method="POST">
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial" value="${fechaInicial}"/>
    <label>Fecha final</label>
    <input type="date" name="fechaFinal" value="${fechaFinal}"/>
    <input type="submit"  value="Mostrar"/>
</form>
    
<div>
    <a href="${pageContext.request.contextPath}/ServletExportar?reporte=reporteMuebleMenosVendido&fechaInicial=${fechaInicial}&fechaFinal=${fechaFinal}">EXPORTAR REPORTE</a>
</div>

<table>
    <tr>
        <td>MUEBLE</td>
        <td>${nombreMueble}</td>
    </tr>
    <tr>
        <td>UNIDADES VENDIDAS</td>
        <td>${unidadesVendidas}</td>
    </tr>
</table>

<table>
    <thead>
        <tr>
            <th>FECHA DE VENTA</th>
            <th>NO. FACTURA</th>
            <th>ID. PRODUCTO</th>
            <th>PRECIO DE VENTA</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="registros" items="${registros}">
            <tr>
                <td>${registros.fechaVenta}</td>
                <td>${registros.numFactura}</td>
                <td>${registros.idEnsamble}</td>
                <td>${registros.precioVenta}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>