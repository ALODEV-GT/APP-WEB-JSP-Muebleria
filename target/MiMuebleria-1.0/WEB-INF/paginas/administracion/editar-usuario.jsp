<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3 contenedor">EDITAR USUARIO</h2>
    <form  action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=modificarUsuario&nombreUsuario=${usuario.nombre}" method="POST">
        <div>
            <label class="my-3">Nombre de usuario</label>
            <input style="width: 200px" type="text" disabled name="nuevoNombreUsuario" required value="${usuario.nombre}"/>
        </div>
        <div>
            <label class="my-3">Area actual</label>
            <input type="text" disabled  name="areaActual" required value="${usuario.area}"/>
        </div>
        <div>
            <label class="my-3"> Nueva area </label>
            <select style="width: 259px" name="area">
                <c:forEach var="areas" items="${areas}">
                    <option>${areas}</option>
                </c:forEach>
            </select>
        </div>
        <div class="my-4">
            <input class="btn btn-outline-dark" type="submit" value="Guardar cambio" />
        </div>
    </form>
</div>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>