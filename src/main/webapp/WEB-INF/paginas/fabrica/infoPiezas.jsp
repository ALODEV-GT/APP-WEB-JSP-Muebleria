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
            <th> </th>
            <th> </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="todasLasPiezas" items="${todasLasPiezas}">
            <tr>
                <td>${todasLasPiezas.idPieza}</td>
                <td>${todasLasPiezas.tipoPieza}</td>
                <td>${todasLasPiezas.precio}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accionFabrica=editarPieza&idPieza=${todasLasPiezas.idPieza}">Editar</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accionFabrica=eliminarPieza&idPieza=${todasLasPiezas.idPieza}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/ServletControlador?accionFabrica=agregarPieza">Agregar pieza</a>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
