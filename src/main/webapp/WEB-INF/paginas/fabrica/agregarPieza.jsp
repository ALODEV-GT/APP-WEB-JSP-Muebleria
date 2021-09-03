<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3">Agrega una nueva pieza</h2>
    <form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=agregarPieza" method="POST">
        <div class="my-3">
            <label for="tipoPieza">Tipo de pieza</label>
            <select name="tipoPieza" >
                <c:forEach var="tipoPiezas" items="${tipoPiezas}">
                    <option value="${tipoPiezas.nombre}">${tipoPiezas.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="my-3">
            <label for="precioPieza">Precio</label>
            <input type="number" name="precioPieza" required step="any" />
        </div>
        <div class="my-4">
            <button class="btn btn-outline-success" type="submit">Agregar</button>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>