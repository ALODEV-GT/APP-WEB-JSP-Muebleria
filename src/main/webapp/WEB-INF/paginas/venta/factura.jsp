<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<div class="container my-5 col-md-3" style="background-color: #EBEBEB; color: #BC6C25; font-family: fantasy; border: 1px solid #fde5cf;">
    <br>
    <h2 style="text-align: center;">Factura #${numFactura}</h2>
    <form>
        <div>
            <label><b>Nit</b></label>
            <input style="border: none; color: #BC6C25; width: 349px;" name="nit"  type="text" value="${clienteF.nit}" required disabled/>
        </div>
        <div>
            <label><b>Nombre</b></label>
            <input style="border: none; color: #BC6C25; width: 315px;" type="text" name="nombreCliente" value="${clienteF.nombre}"  disabled/>
        </div>
        <div>
            <label><b>Direccion</b></label>
            <input style="border: none;color: #BC6C25; width: 305px;" type="text" name="direccion"  value="${clienteF.direccion}" disabled/>
        </div>
        <div>
            <label><b>Municipio</b></label>
            <input style="border: none; color: #BC6C25; width: 300px;" type="text" name="municipio" value="${clienteF.municipo}" disabled/>
        </div>
        <div>
            <label><b>Departamento</b></label>
            <input style="border: none; color: #BC6C25; width: 272px;" type="text" name="departamento" value="${clienteF.departamento}" disabled />
        </div>

        <table style="color: #BC6C25; font-family: fantasy" class="table">
            <thead>
                <tr>
                    <th>No. Detalle</th>
                    <th>Id. Producto</th>
                    <th>Producto</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="detalles" items="${detalles}">
                    <tr>
                        <td>${detalles.numDetalle}</td>
                        <td>${detalles.idEnsamble}</td>
                        <td>${detalles.nombreProducto}</td>
                        <td>${detalles.precio}</td>
                    </tr>
                </c:forEach>
                <tr><th colspan="3">Total</th>
                    <th>${total}</th>
                </tr>
            </tbody>
        </table>
    </form>
</div>

<a href="${pageContext.request.contextPath}/ServletControlador?paginaVenta=inicio">REGRESAR</a>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>