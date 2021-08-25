<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=agregarTipoPieza" method="POST">
    <label for="nombreTipoPieza"></label>
    <input name="nombreNuevoTipoPieza" type="text" required  />
    <button type="submit">Agregar</button>
</form>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>