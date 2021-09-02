<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<h1>AGREGAR MUEBLE</h1>
<form  action="${pageContext.request.contextPath}/ServletControlador?accionAdministracion=agregarRequerimiento" method="POST">
    
    <label>Mueble</label>
    <select name="mueble">
        <c:forEach var="muebles" items="${muebles}">
            <option>${muebles.nombre}</option>
        </c:forEach>
    </select>
    
    <label>Requerimiento</label>
    <select name="tipoPieza">
        <c:forEach var="tipoPiezas" items="${tipoPiezas}">
            <option>${tipoPiezas.nombre}</option>
        </c:forEach>
    </select>
    
    <label>Cantidad necesaria</label>
    <input type="number"   name="cantidad" required />
    
    <input type="submit" value="Guardar" />
</form>

<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>