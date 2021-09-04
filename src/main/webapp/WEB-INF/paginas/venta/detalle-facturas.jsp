<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center">
    <h1 class="contenedor"> DETALLE DE FACTURAS </h1>
    <form action="${pageContext.request.contextPath}/ServletControlador?accionVentas=detalleFacturas" method="POST">
        <div>
            <label>Nit cliente</label>
            <input type="text" name="nitCliente"  />
        </div>
        <div class="my-2">
            <label>Numero de factura</label>
            <input style="width: 116px" type="number" name="numFactura"/>
            <input type="submit"  value="Mostrar"/>
        </div>
    </form>
    <hr>
</div>
<c:forEach var="facturas" items="${facturas}">
    <div class="container my-5 col-md-3" style="background-color: #EBEBEB; color: #BC6C25; font-family: fantasy; border: 1px solid #fde5cf;">
        <br>
        <h2 style="text-align: center;">Factura #${facturas.numFactura} </h2>

        <div>
            <label><b>Nit:</b></label>
            <input disabled style="border: none; color: #BC6C25; width: 349px; background-color: #EBEBEB;" type="text" value="${facturas.nitCliente}" />
        </div>

        <div>
            <label><b>Nombre:</b></label>
            <input disabled style="border: none; color: #BC6C25; width: 300px; background-color: #EBEBEB;" type="text" value="${facturas.nombreCliente}" />
        </div>

        <div>
            <label><b>Fecha:</b></label>
            <input disabled style="border: none; color: #BC6C25; width: 300px; background-color: #EBEBEB;" type="text" value="${facturas.fecha}" />
        </div>

        <div>
            <label><b>Vendedor:</b></label>
            <input disabled style="border: none; color: #BC6C25; width: 300px; background-color: #EBEBEB;" type="text" value="${facturas.vendedor}"/>
        </div>

        <table style="color: #BC6C25; font-family: fantasy" class="table">
            <thead>
                <tr>
                    <th># detalle </th>
                    <th>Id. Producto</th>
                    <th>Producto </th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="detalles" items="${facturas.detalles}">
                    <tr>
                        <td>${detalles.numDetalle}</td>
                        <td>${detalles.idEnsamble}</td>
                        <td>${detalles.nombreProducto}</td>
                        <td>${detalles.precio}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2"><b>Total</b><td>
                    <td><b>${facturas.total}</b></td>
                </tr>        
            </tbody>
        </table>
        <br>
        <br>
    </div>
</c:forEach>

<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>