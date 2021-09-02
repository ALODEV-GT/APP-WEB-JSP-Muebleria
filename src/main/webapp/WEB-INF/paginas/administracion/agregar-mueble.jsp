<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<h1>AGREGAR MUEBLE</h1>
<form  action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarMueble" method="POST">
    <label>Nombre del mueble</label>
    <input type="text"  name="nombreNuevoMueble" required/>
    <label>Precio</label>
    <input type="text"   name="precio" required />
    <input type="submit" value="Guardar" />
</form>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>