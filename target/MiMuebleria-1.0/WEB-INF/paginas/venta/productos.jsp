<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>
<div class="container my-5">
    <h1>Productos</h1>
    <table class="table">
        <thead class="table-dark">
            <tr>
                <th>Identificador</th>
                <th>Nombre del mueble</th>
                <th>Precio venta</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="productos" items="${productos}">
                <tr>
                    <td>${productos.idEnsamble}</td>
                    <td>${productos.tipoMueble.toUpperCase()}</td>
                    <td>${productos.precioVenta}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>
