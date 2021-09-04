<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<div class="container col-md-5 my-5" style="text-align: center;">
    <h1>REPORTES</h1>
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteVentas">REPORTE DE VENTAS</a>
    </div>
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteDevoluciones">REPORTE DE DEVOLUCIONES</a>
    </div >
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteGanancias">REPORTE DE GANANCIAS</a>
    </div>
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteUsuarioMasVentas">REPORTE DE USUARIO CON MAS VENTAS</a>
    </div>
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteUsuarioMasGanancias">REPORTE DE USUARIO CON MAS GANANCIAS</a>
    </div>
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteMuebleMasVendido">REPORTE DE MUEBLE MAS VENDIDO</a>
    </div>
    <div class="my-3 d-grid grap-2">
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteMuebleMenosVendido">REPORTE DE MUEBLE MENOS VENDIDO</a>
    </div>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>