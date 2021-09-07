<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<div class="container my-5">
    <div class="d-flex">
        <div style="margin-right: 10px ">
            <h1>REPORTE DE DEVOLUCIONES</h1>
        </div>

        <div class=" d-flex align-items-center me-auto">
            <form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=reporteDevoluciones" method="POST">
                <label>Fecha inicial</label>
                <input style="width: 140px; " type="date" name="fechaInicial" value="${fechaInicial}"/>
                <label>Fecha final</label>
                <input style="width: 140px; "  type="date" name="fechaFinal" value="${fechaFinal}"/>
                <input type="submit"  value="Mostrar"/>
            </form>
        </div>
        <div class=" d-flex align-items-center">
            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/ServletExportar?reporte=devoluciones&fechaInicial=${fechaInicial}&fechaFinal=${fechaFinal}">EXPORTAR REPORTE</a>
        </div>
    </div>


    <div class="contenedor d-inline-flex">
        NUMERO DE DEVOLUCIONES:
        ${numDevoluciones}
    </div>
    <div class="contenedor d-inline-flex">
        TOTAL DE PERDIDA:
        ${totalPerdida}
    </div>
    <br>
    <br>


    <table class="table">
        <thead class="table-dark">
            <tr>
                <th>NO. FACTURA</th>
                <th>FECHA DE VENTA</th>
                <th>FECHA DE DEVOLUCION</th>
                <th>NOMBRE DEL CLIENTE</th>
                <th>VENDEDOR</th>
                <th>PRODUCTO</th>
                <th>PERDIDA</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="registros" items="${registros}">
                <tr>
                    <td>${registros.numFactura}</td>
                    <td>${registros.fechaVenta}</td>
                    <td>${registros.fechaDevolucion}</td>
                    <td>${registros.nombreCliente}</td>
                    <td>${registros.usuarioVendedor}</td>
                    <td>${registros.nombreProducto}</td>
                    <td>${registros.perdida}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>

        <jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
        <jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>