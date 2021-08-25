<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>
<h3>TODAS LAS PIEZAS</h3>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="todasLasPiezas" items="${todasLasPiezas}">
        <tr>
            <td>${todasLasPiezas.idPieza}</td>
            <td>${todasLasPiezas.tipoPieza}</td>
            <td>${todasLasPiezas.precio}</td>
        </tr>
    </c:forEach>
</tbody>
</table>


<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
