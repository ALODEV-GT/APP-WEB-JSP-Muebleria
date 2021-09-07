<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3 contenedor">AGREGAR MUEBLE</h2>
    <form  action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarMueble" method="POST">
        <div>
            <label class="my-3">Nombre del mueble</label>
            <input type="text"  name="nombreNuevoMueble" required/>
        </div>
        <div>
            <label>Precio</label>
            <input style="width: 355px" type="text"   name="precio" required />
        </div>
        <div class="my-4">
            <input class="btn btn-outline-dark" type="submit" value="Guardar" />
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>