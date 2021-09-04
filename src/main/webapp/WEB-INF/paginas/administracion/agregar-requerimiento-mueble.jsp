<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3 contenedor">AGREGAR REQUERIMIENTO A MUEBLE</h2>
    <form  action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarRequerimiento" method="POST">
        <div>
            <label class="my-3">Mueble</label>
            <select name="mueble" style="width: 270px">
                <c:forEach var="muebles" items="${muebles}">
                    <option>${muebles.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label>Requerimiento</label>
            <select name="tipoPieza">
                <c:forEach var="tipoPiezas" items="${tipoPiezas}">
                    <option>${tipoPiezas.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="my-3">
            <label>Cantidad necesaria</label>
            <input style="width: 187px" type="number"   name="cantidad" required />
        </div>
        <div class="my-4">
            <input class="btn btn-outline-dark" type="submit" value="Guardar" />
        </div>
    </form>
</div>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>