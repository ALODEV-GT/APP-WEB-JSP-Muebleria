<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<div class="container my-5">
    <h1>CREACION DE MUEBLES</h1>

    <div class="d-flex align-items-start">
        <div>
            <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarMueble">Agregar mueble 
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 15 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/> 
                </svg>
            </a>
        </div>
        <div style="padding-right: 8px "></div>
        <div>
            <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarRequistoMueble">Agregar requerimiento a mueble 
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 15 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/> 
                </svg>
            </a>
        </div>
    </div>
    <br>
        <table class="table">
            <thead class="table-dark">
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
                            <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=editarMueble&nombreMueble=${muebles.nombre}">Editar precio 
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                    <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                </svg>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>


<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>