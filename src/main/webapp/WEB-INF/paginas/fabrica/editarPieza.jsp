<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=actualizarPieza&idPieza=${modeloPieza.idPieza}" method="POST">
    <label for="nombrePieza" >Nombre de pieza</label>
    <input type="text" name="nombrePieza" disabled required value="${modeloPieza.tipoPieza}"/>
    <label for="precioPieza">Precio</label>
    <input type="number" name="precioPieza" required step="any" value="${modeloPieza.precio}" />
    <button type="submit">Guardar cambios</button>
</form>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>