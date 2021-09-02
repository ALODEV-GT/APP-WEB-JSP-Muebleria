<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>
<h3>PRODUCCION</h3>

<a href="${pageContext.request.contextPath}/ServletControlador?paginaFabrica=produccion&orden=asc">Ascendente</a>
<a href="${pageContext.request.contextPath}/ServletControlador?paginaFabrica=produccion&orden=desc">Descendente</a>

<table>
    <tbody>
        <c:forEach var="ensamblesMueble" items="${ensamblesMueble}">
            <tr><td>${ensamblesMueble.tipoMueble.toUpperCase()}<td></tr>
            <tr>
                <td>A este mueble se le asigno el Id. de producto ${ensamblesMueble.idEnsamble} y fue ensamblado el ${ensamblesMueble.fechaEnsambre} por el fabricador ${ensamblesMueble.ensamblador} , y se utilizaron las siguientes piezas:</td>
            </tr>
            <c:forEach var="piezasUsadas" items="${ensamblesMueble.piezasUsadas}">
                <tr>
                    <td>Se uso una ${piezasUsadas.nombrePieza} con un costo de ${piezasUsadas.precioPieza}.</td>
                </tr>
            </c:forEach>
            <tr><td>El costo total del mueble es de ${ensamblesMueble.costo}</td></tr>
            <tr><td><br></td></tr>
            <tr><td><br></td></tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
