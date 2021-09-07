<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/administracion/menu.jsp"/>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3 contenedor">CARGA DE DATOS</h2>
    <form name="formulario" method="POST" action="${pageContext.request.contextPath}/ServletCargaArchivo" enctype="multipart/form-data">
        <div class="my-2">
            <input  type="file" name="archivoCargaDatos" accept=".txt">
        </div>
        <div class="my-3">
            <input class="btn btn-success" type="submit" value="Enviar" />
        </div>
    </form>
</div>

<div class="container col-md-4 my-5" style="text-align: center; border: 2px solid #000; border-radius: 15px">
    <h2 class="my-3 contenedor">ERRORES</h2>
    <table class="table">
        <c:forEach var="errores" items="${errores}">
            <tr>
                <td>${errores}</td>
            </tr>
        </c:forEach>
    </table>
</div>


<jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>