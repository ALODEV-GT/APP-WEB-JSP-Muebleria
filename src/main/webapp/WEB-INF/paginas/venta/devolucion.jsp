<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>
<h1>Devolucion</h1>

<form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=devolver" method="POST">
    <label>No. Factura</label>
    <input type="number" required name="numFactura"/>
    <label>Id. Mueble a devolver</label>
    <input type="number" required name="idMueble"/>
    <input type="submit" value="Devolver"/>
</form>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>