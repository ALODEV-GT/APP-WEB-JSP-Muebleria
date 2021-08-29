<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>
<h3>MATERIA PRIMA</h3>
<a href="${pageContext.request.contextPath}/ServletControlador?accionFabrica=inventario">INVENTARIO DE PIEZAS</a>

<a href="${pageContext.request.contextPath}/ServletControlador?paginaFabrica=materiaPrima&orden=asc">Ascendente</a>
<a href="${pageContext.request.contextPath}/ServletControlador?paginaFabrica=materiaPrima&orden=desc">Descendente</a>


<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="tp" items="${tipoPiezasDisponibles}">
            <tr>
                <td>${tp.idTipoPieza}</td>
                <td>${tp.nombre}</td>
                <td>${tp.cantidad}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accionFabrica=editarTipoPieza&idTipoPieza=${tp.idTipoPieza}">Editar</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accionFabrica=eliminarTipoPieza&idTipoPieza=${tp.idTipoPieza}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/ServletControlador?accionFabrica=agregarTipoPieza">Agregar un nuevo tipo de pieza</a>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
