<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>CARGA DE DATOS</h1>
<form name="formulario" method="POST" action="${pageContext.request.contextPath}/ServletCargaArchivo" enctype="multipart/form-data">
    <input type="file" name="archivoCargaDatos" accept=".txt">
    <input type="submit" value="Enviar" />
</form>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>