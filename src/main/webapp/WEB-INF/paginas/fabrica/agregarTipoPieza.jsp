<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<div class="container col-md-3 my-5" style="text-align: center; border: 1px solid #000; border-radius: 15px">
    <h2 class="my-3">Agrega una nueva pieza</h2>
    <form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=agregarTipoPieza" method="POST">
        <label class="my-3" for="nombreTipoPieza">Nombre de nuevo pieza</label>
        <input class="my-2" name="nombreNuevoTipoPieza" type="text" required  />
        <div class="my-4">
            <input class="btn btn-outline-success" type="submit" value="Agregar"/>
        </div>
    </form>

</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>