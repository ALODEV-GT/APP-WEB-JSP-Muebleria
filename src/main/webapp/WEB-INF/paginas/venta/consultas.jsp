<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>
<h1>Consultas</h1>

<ul>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=compraClientes">COMPRAS DE CLIENTES</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=devolucionClientes">DEVOLUCIONES DE CLIENTES</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=detalleFacturas">DETALLE DE FACTURAS</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=ventasDiarias">VENTAS DEL DIA</a></li>
</ul>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
