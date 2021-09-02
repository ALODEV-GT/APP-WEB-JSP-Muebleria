<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>MANEJO DE USUARIOS</h1>

<table>
    <thead>
        <tr>
            <th>Nombre de usuario</th>
            <th>Area</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="usuarios" items="${usuarios}">
            <tr>
                <td>${usuarios.nombre}</td>
                <td>${usuarios.area}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=editarUsuario&nombreUsuario=${usuarios.nombre}">Cambiar area</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=eliminarUsuario&nombreUsuario=${usuarios.nombre}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarUsuario">Agregar usuario</a>


<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>