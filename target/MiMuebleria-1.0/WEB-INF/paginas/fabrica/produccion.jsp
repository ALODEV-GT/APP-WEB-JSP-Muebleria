<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<div class="container col-md-4 my-5">
    <div class="contenedor d-flex align-items-start">
        <div class="me-auto">
            <h3>PRODUCCION</h3>
        </div>
        <div>
            <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/ServletControlador?paginaFabrica=produccion&orden=asc">Ascendente 
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-arrow-up-short" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"/>
                </svg>
            </a>
        </div>
        <div>
            <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/ServletControlador?paginaFabrica=produccion&orden=desc">Descendente 
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-arrow-down-short" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v5.793l2.146-2.147a.5.5 0 0 1 .708.708l-3 3a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L7.5 10.293V4.5A.5.5 0 0 1 8 4z"/>
                </svg>
            </a>
        </div>
    </div>
    <table class="table">
        <tbody>
            <c:forEach var="ensamblesMueble" items="${ensamblesMueble}">
                <tr><th>${ensamblesMueble.tipoMueble.toUpperCase()}</th></tr>
                <tr>
                    <td>A este mueble se le asigno el Id. de producto ${ensamblesMueble.idEnsamble} y fue ensamblado el ${ensamblesMueble.fechaEnsambre} por el fabricador ${ensamblesMueble.ensamblador} , y se utilizaron las siguientes piezas:</td>
                </tr>
                <c:forEach var="piezasUsadas" items="${ensamblesMueble.piezasUsadas}">
                    <tr>
                        <td>Se uso una ${piezasUsadas.nombrePieza} con un costo de ${piezasUsadas.precioPieza}.</td>
                    </tr>
                </c:forEach>
                <tr><td>El costo total del mueble es de <span style="font-weight: bold">${ensamblesMueble.costo}</span></td></tr>
                <tr><td><br></td></tr>
                <tr><td><br></td></tr>
            </c:forEach>
        </tbody>
    </table>
</div>


<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
