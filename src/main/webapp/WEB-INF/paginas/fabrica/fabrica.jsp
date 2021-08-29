<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<h3>Piezas agotadas o apunto de agotarse</h3>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="piezasPorAgotar" items="${piezasPorAgotar}">
            <tr>
                <td>${piezasPorAgotar.idTipoPieza}</td>
                <td>${piezasPorAgotar.nombre}</td>
                <td>${piezasPorAgotar.cantidad}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>