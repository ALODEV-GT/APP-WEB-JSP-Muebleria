<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>INICIO</h1>

<ul>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteVentas">REPORTE DE VENTAS</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteDevoluciones">REPORTE DE DEVOLUCIONES</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteGanancias">REPORTE DE GANANCIAS</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteUsuarioMasVentas">REPORTE DE USUARIO CON MAS VENTAS</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteUsuarioMasGanancias">REPORTE DE USUARIO CON MAS GANANCIAS</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteMuebleMasVendido">REPORTE DE MUEBLE MAS VENDIDO</a></li>
    <li><a href="${pageContext.request.contextPath}/ServletControlador?consultasAdministracion=reporteMuebleMenosVendido">REPORTE DE MUEBLE MENOS VENDIDO</a></li>
</ul>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>