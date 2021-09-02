<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>CREACION DE MUEBLES</h1>

<table>
    <thead>
        <tr>
            <th>Nombre del mueble</th>
            <th>Precio de venta</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="muebles" items="${muebles}">
            <tr>
                <td>${muebles.nombre}</td>
                <td>${muebles.precio}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=editarMueble&nombreMueble=${muebles.nombre}">Editar precio</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarMueble">Agregar mueble</a>
<a href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarRequistoMueble">Agregar requerimiento a mueble</a>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>