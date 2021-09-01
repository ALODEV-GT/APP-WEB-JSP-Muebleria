<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>REPORTE USUARIO MAS GANANCIAS</h1>
<form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=" method="POST">
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial" />
    <label>Fecha final</label>
    <input type="date" name="fechaFinal" />
    <input type="submit"  value="Mostrar"/>
</form>
<div>
    <a href="/PruebaCSV/Servlet">EXPORTAR REPORTE</a>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>