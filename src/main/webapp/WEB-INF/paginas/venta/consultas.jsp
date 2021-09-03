<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<div class="container col-md-5 my-5" style="text-align: center;">
    <br>
    <h1>Consultas</h1>
    <div >
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=compraClientes">COMPRAS DE CLIENTES</a>
    </div>
    <div class="my-3 d-grid grap-2"">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=devolucionClientes">DEVOLUCIONES DE CLIENTES</a>
    </div>
    <div class="my-3 d-grid grap-2"">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=detalleFacturas">DETALLE DE FACTURAS</a>
    </div>
    <div class="my-3 d-grid grap-2"">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultaVenta=ventasDiarias">VENTAS DEL DIA</a>
    </div>
    </div>
</div>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
