<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<form action="${pageContext.request.contextPath}/ServletControlador?accionFabrica=agregarPieza" method="POST">
    <select name="tipoPieza" >
        <c:forEach var="tipoPiezas" items="${tipoPiezas}">
            <option value="${tipoPiezas.nombre}">${tipoPiezas.nombre}</option>
        </c:forEach>
    </select>
    <label for="precioPieza">Precio</label>
    <input type="number" name="precioPieza" required step="any" />
    <button type="submit">Agregar</button>
</form>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>