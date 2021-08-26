<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<h1>HOLA ${nombreUsuario}  ERES UN ADMINISTRADOR</h1>
<h2>Eres del area de ${area} </h2> 
<form name="formulario" method="POST" action="${pageContext.request.contextPath}/ServletCargaArchivo" enctype="multipart/form-data">
    <input type="file" name="archivoCargaDatos" accept=".txt">
    <input type="submit" value="Enviar" />
</form>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
