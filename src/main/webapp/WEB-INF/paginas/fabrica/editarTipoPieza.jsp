<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3 contenedor">Editar el nombre de la pieza</h2>
    <form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=actualizarTipoPieza&idTipoPieza=${modeloTipoPieza.idTipoPieza}" method="POST">
        <label class="my-3" for="nombreTipoPieza" >Nombre de pieza</label>
        <div>
            <input type="text" name="nombreTipoPieza" required value="${modeloTipoPieza.nombre}"/>
        </div>
        <div class="my-4">
            <button class="btn btn-outline-dark" type="submit">Guardar cambios</button>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>