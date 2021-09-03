<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3">Edita el precio de la pieza</h2>
    <form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=actualizarPieza&idPieza=${modeloPieza.idPieza}" method="POST">
        <label class="my-3" for="nombrePieza" >Nombre de pieza</label>
        <input type="text" name="nombrePieza" disabled required value="${modeloPieza.tipoPieza}"/>
        <div>
            <label for="precioPieza">Precio</label>
            <input type="number" name="precioPieza" required step="any" value="${modeloPieza.precio}" />
        </div>
        <div class="my-4">
            <input class="btn btn-outline-dark" type="submit" value="Guardar cambio" />
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>