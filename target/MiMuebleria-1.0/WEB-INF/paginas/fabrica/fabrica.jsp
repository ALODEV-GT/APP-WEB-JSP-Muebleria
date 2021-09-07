<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/fabrica/menu.jsp"/>

<br>
<div class="container col-md-8 my-5">
    <h3 >PIEZAS AGOTADAS O APUNTO DE AGOTARSE</h3>
    <br>
    <table class="table">
        <thead class="table-dark">
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
                    <td style="color: #e63946">${piezasPorAgotar.cantidad}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>