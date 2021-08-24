<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<form action="${pageContext.request.contextPath}/ServletControlador?accion=actualizarTipoPieza">
    <label for="nombre" >Nombre de pieza</label>
    <input type="text" class="form-control" name="nombre" required value="${modeloTipoPieza.nombre}"/>
    <button type="submit">Guardar</button> 
</form>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>