<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<div class="container my-5">
    <div style="margin-right: 10px ">
        <h1>REPORTE DEL USUARIO CON MAS GANANCIAS</h1>
    </div>
    <div class="d-flex my-2">
        <div class=" d-flex align-items-center me-auto">
            <form action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=reporteUsuarioMasGanancias" method="POST">
                <label>Fecha inicial</label>
                <input style="width: 140px;" type="date" name="fechaInicial" value="${fechaInicial}"/>
                <label>Fecha final</label>
                <input style="width: 140px;" type="date" name="fechaFinal" value="${fechaFinal}"/>
                <input type="submit"  value="Mostrar"/>
            </form>
        </div>
        <div class=" d-flex align-items-center">
            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/ServletExportar?reporte=reporteUsuarioMasGanancias&fechaInicial=${fechaInicial}&fechaFinal=${fechaFinal}">EXPORTAR REPORTE</a>
        </div>
    </div>

        
    <div class="contenedor d-inline-flex">
        USUARIO:
        ${vendedor}
    </div>
    <div class="contenedor d-inline-flex">
        GANANCIA TOTAL:
        ${totalGanancias}
    </div>
    <br>
    <br>
    <table class="table">
        <thead class="table-dark">
            <tr>
                <th>FECHA DE VENTA</th>
                <th>PRODUCTO</th>
                <th>GANANCIA</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="registros" items="${registros}">
                <tr>
                    <td>${registros.fechaVenta}</td>
                    <td>${registros.nombreProducto}</td>
                    <td>${registros.ganancia}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
    <jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>