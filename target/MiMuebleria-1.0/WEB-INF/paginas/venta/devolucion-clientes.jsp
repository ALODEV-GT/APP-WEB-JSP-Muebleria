<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<div class="container my-5">
    <h1> DEVOLUCION CLIENTES </h1>

    <form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=devolucionesClientes" method="POST">
        <label>Nit</label>
        <input type="text" name="nitCliente" value="${nitCliente}"/>
        <label>Fecha inicial</label>
        <input type="date" name="fechaInicial" value="${fechaInicial}"/>
        <label>Fecha final</label>
        <input type="date" name="fechaFinal" value="${fechaFinal}"/>
        <input type="submit"  value="Mostrar"/>
    </form>

        <br>
    <table class="table">
        <thead class="table-dark">
            <tr>
                <th># Factura </th>
                <th>Nombre del cliente</th>
                <th>Nit</th>
                <th>Fecha devolucion</th>
                <th>Id. Proucto</th>
                <th>Producto </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="devoluciones" items="${devoluciones}">
                <tr>
                    <td>${devoluciones.numFactura}</td>
                    <td>${devoluciones.nombreCliente}</td>
                    <td>${devoluciones.nitCliente}</td>
                    <td>${devoluciones.fecha}</td>
                    <td>${devoluciones.idDevolucion}</td>
                    <td>${devoluciones.productoDevuelto}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>

        <jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>