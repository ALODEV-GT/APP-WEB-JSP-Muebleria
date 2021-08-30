<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<form>
    <fieldset>
        <legend>FACTURA</legend>
        <div>
            <label>Nit</label>
            <input name="nit"  type="text" value="${clienteF.nit}" required disabled/>
        </div>
        <div>
            <label>Nombre</label>
            <input type="text" name="nombreCliente" value="${clienteF.nombre}"  disabled/>
        </div>
        <div>
            <label>Direccion</label>
            <input type="text" name="direccion"  value="${clienteF.direccion}" disabled/>
        </div>
        <div>
            <label>Municipio</label>
            <input type="text" name="municipio" value="${clienteF.municipo}" disabled/>
        </div>
        <div>
            <label>Departamento</label>
            <input type="text" name="departamento" value="${clienteF.departamento}" disabled />
        </div>

        <table>
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
                <tr><td colspan="3">Total</td>
                    <td>${total}</td>
                </tr>
            </tbody>
        </table>
    </fieldset>
</form>

<a href="${pageContext.request.contextPath}/ServletControlador?paginaVenta=inicio">REGRESAR</a>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>