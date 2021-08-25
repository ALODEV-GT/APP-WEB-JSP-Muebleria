<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=actualizarTipoPieza&idTipoPieza=${modeloTipoPieza.idTipoPieza}" method="POST">
    <label for="nombreTipoPieza" >Nombre de pieza</label>
    <input type="text" name="nombreTipoPieza" required value="${modeloTipoPieza.nombre}"/>
    <button type="submit">Guardar cambios</button>
</form>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>