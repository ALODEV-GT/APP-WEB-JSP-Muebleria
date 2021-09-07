<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>
<div class="container col-md-4 my-5" style="text-align: center; border: 1px solid #000; border-radius: 15px">
    <h1 class="my-3 contenedor">Devolucion</h1>

    <form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=devolver" method="POST">
        <label class="my-3">No. Factura</label>
        <input type="number" required name="numFactura"/>
        <div>
            <label>Id. Mueble a devolver</label>
            <input style="width: 185px" type="number" required name="idMueble"/>
        </div>
        <div class="my-3">
            <input class="btn btn-outline-dark" type="submit" value="Devolver"/>
        </div>
    </form>
</div>
    <jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>