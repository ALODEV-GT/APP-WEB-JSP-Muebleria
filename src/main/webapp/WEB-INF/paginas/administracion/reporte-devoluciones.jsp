<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>REPORTE DEVOLUCIONES</h1>
<form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=reporteDevoluciones" method="POST">
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial" value="${fechaInicial}"/>
    <label>Fecha final</label>
    <input type="date" name="fechaFinal" value="${fechaFinal}"/>
    <input type="submit"  value="Mostrar"/>
</form>
<div>
    <a href="${pageContext.request.contextPath}/ServletExportar?reporte=devoluciones&fechaInicial=${fechaInicial}&fechaFinal=${fechaFinal}">EXPORTAR REPORTE</a>
</div>

<table>
    <tr>
        <td>NUMERO DE DEVOLUCIONES</td>
        <td>${numDevoluciones}</td>
    </tr>
    <tr>
        <td>TOTAL DE PERDIDA</td>
        <td>${totalPerdida}</td>
    </tr>
</table>
    
<table>
    <thead>
        <tr>
            <th>NO. FACTURA</th>
            <th>FECHA DE VENTA</th>
            <th>FECHA DE DEVOLUCION</th>
            <th>NOMBRE DEL CLIENTE</th>
            <th>VENDEDOR</th>
            <th>PRODUCTO</th>
            <th>PERDIDA</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="registros" items="${registros}">
            <tr>
                <td>${registros.numFactura}</td>
                <td>${registros.fechaVenta}</td>
                <td>${registros.fechaDevolucion}</td>
                <td>${registros.nombreCliente}</td>
                <td>${registros.usuarioVendedor}</td>
                <td>${registros.nombreProducto}</td>
                <td>${registros.perdida}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>