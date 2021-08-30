<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<h1> DEVOLUCION CLIENTES </h1>

<form action="" method="POST">
    <label>Nit</label>
    <input type="text" name="nitCliente"/>
    <label>Fecha inicial</label>
    <input type="date" name="fechaInicial"/>
    <label>Fecha final</label>
    <input type="date" name="fechaFinal"/>
    <input type="submit"  value="Mostrar"/>
    <input type ="submit" value="Mostrar todo" />
</form>

<c:forEach var=" " items="">
    
</c:forEach>
<table>
    <thead>
        <tr>
            <th>Fecha</th>
            <th># Factura </th>
            <th>Id. Producto</th>
            <th>Producto </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>fecha</td>
            <td>numFactura</td>
            <td>idProdu</td>
            <td>producto</td>
        </tr>
    </tbody>
</table>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>