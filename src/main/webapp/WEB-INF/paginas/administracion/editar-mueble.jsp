<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>
<h1>EDITAR MUEBLE</h1>
<form  action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=editarMueble&nombreMueble=${mueble.nombre}" method="POST">
    <label>Nombre del mueble</label>
    <input type="text" disabled name="nombreMueble" required value="${mueble.nombre}"/>
    <label>Precio</label>
    <input type="text"   name="precio" required value="${mueble.precio}"/>
    <input type="submit" value="Guardar" />
</form>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>